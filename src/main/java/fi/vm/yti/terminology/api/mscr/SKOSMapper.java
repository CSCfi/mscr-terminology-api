package fi.vm.yti.terminology.api.mscr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.SKOS;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;

import fi.vm.yti.terminology.api.model.ntrf.DEF;
import fi.vm.yti.terminology.api.model.ntrf.ECON;
import fi.vm.yti.terminology.api.model.ntrf.LANG;
import fi.vm.yti.terminology.api.model.ntrf.Languages;
import fi.vm.yti.terminology.api.model.ntrf.RECORD;
import fi.vm.yti.terminology.api.model.ntrf.TE;
import fi.vm.yti.terminology.api.model.ntrf.TERM;
import fi.vm.yti.terminology.api.model.ntrf.VOCABULARY;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;


public class SKOSMapper {

	private List<Resource> getLeafConcepts(Model inputModel) {
		List<Resource> r = new ArrayList<Resource>();

		String queryString = 
				"prefix skos: <http://www.w3.org/2004/02/skos/core#> " + 
				"select ?uri " +
				"where { " +
				"  ?uri a skos:Concept; " +
				"  minus { "+
				"    ?uri skos:narrower ?other . " +
				"    ?other2 skos:broader ?uri . " +
				"  } "+
				"}";
		Query qry = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(qry, inputModel);
		ResultSet rs = qe.execSelect();
		while (rs.hasNext()) {
			QuerySolution qs = rs.next();
			r.add(inputModel.getResource(qs.get("uri").toString()));
		}
		return r;
	}
	
    public static String propertyToString(Resource resource, Property property){
        var prop = resource.getProperty(property, "en");
        //null check for property
        if(prop == null){
            return null;
        }
        var object = prop.getObject();
        //null check for object
        return object == null ? null : object.toString();
    }
    
	private String getPrefLabel(Resource r) {

		if (r.getProperty(SKOS.prefLabel, "en") != null) {
			return r.getRequiredProperty(SKOS.prefLabel, "en").getString();
		} else if (r.getProperty(SKOS.prefLabel) != null) {
			return r.getProperty(SKOS.prefLabel).getString();
		} else if (r.getLocalName() != null && !r.getLocalName().equals("")) {
			return r.getLocalName();
		} else {
			return r.getURI();
		}
	}

	private String getLocalName(Resource r) {
		if (r.getLocalName() != null && !r.getLocalName().equals("")) {
			return r.getLocalName();
		} else if (!r.getURI().substring(r.getURI().lastIndexOf("/") + 1).equals("")) {
			return r.getURI().substring(r.getURI().lastIndexOf("/") + 1);
		} else {
			return r.getURI();
		}
	}
	
	private Resource getParent(Resource concept, Model model) {
		if (concept.hasProperty(SKOS.broader)) {
			return concept.getPropertyResourceValue(SKOS.broader);
		} else if (model.listSubjectsWithProperty(SKOS.narrower, concept).hasNext()) {
			return model.listSubjectsWithProperty(SKOS.narrower, concept).next();
		}
		return null;
	}

	private List<Resource> getChildren(Resource concept, Model model) {
		List<Resource> children = new ArrayList<Resource>();
		ResIterator i = model.listSubjectsWithProperty(SKOS.broader, concept);
		NodeIterator i2 = model.listObjectsOfProperty(concept, SKOS.narrower);
		while (i.hasNext()) {
			children.add(i.next());
		}
		while (i2.hasNext()) {
			Resource candidate = i2.next().asResource();
			if (!children.contains(candidate)) {
				children.add(candidate);
			}

		}

		return children;
	}

	private void handleConcept(Resource concept, Model model, VOCABULARY v) {
		String prefLabel = getPrefLabel(concept);
		String description = propertyToString(concept, SKOS.definition);
		if (description == null) {
			description = "";
		}
		String uri = concept.getURI();
		String localName = getLocalName(concept);

		
		RECORD r = new RECORD();
		r.setStat("DRAFT");
		r.setNumb("c" + v.getRECORDAndHEADAndDIAG().size() + 1);
		

		LANG lang = new LANG();
		lang.setValue(Languages.EN);
		
		TE te = new TE();
		te.setStat("DRAFT");
		TERM term = new TERM();
		term.getContent().add(prefLabel);		
		te.setTERM(term);
		
		DEF def = new DEF();
		def.getContent().add(description);
				
		lang.setTE(te);
		lang.getDEF().add(def);		
		
		ECON econ = new ECON();
		econ.setTypr("exactMatch");
		econ.setHref(uri);
		
		r.getLANG().add(lang);
		v.getRECORDAndHEADAndDIAG().add(r);
	}
	
	private void handleConcept(Resource concept, Model model, XSSFSheet v, Set<String> added) {
		String uri = concept.getURI();
		if(added.contains(uri)) {
			return;
		}
		String prefLabel = getPrefLabel(concept);
		String description = propertyToString(concept, SKOS.definition);
		if (description == null) {
			description = "";
		}
		String localName = getLocalName(concept);

				
		 XSSFRow row = v.createRow(v.getLastRowNum() +1 );
		 XSSFCell uriCell = row.createCell(2);
	     uriCell.setCellValue(uri);
		 XSSFCell labelCell = row.createCell(0);
		 labelCell.setCellValue(prefLabel);
		 XSSFCell defCell = row.createCell(1);
		 defCell.setCellValue(description);
		 XSSFCell statusCell = row.createCell(3);
		 statusCell.setCellValue("DRAFT");
		 
		 added.add(uri);

	}
	
	private void traverseUp(Resource r, Model inputModel, XSSFSheet v, Set<String> added) throws Exception {
		// Add n to A to maintain bottom up nature
		if (r == null)
			return;
		// Go to parent
		Resource parent = getParent(r, inputModel);
		if (parent == null) {
			// we are at a top concept
			handleConcept(r, inputModel, v, added);
			return;
		}
		List<Resource> children = getChildren(parent, inputModel);
		handleConcept(parent, inputModel, v, added);
		for (Resource child : children) {
			handleConcept(child, inputModel, v, added);

		}
		// When done with adding all p's children, continue traversing up
		traverseUp(parent, inputModel, v, added);
	}
	
	public File mapToNTRF(InputStream is) throws Exception {
		VOCABULARY v = new VOCABULARY();
		
		Model model = ModelFactory.createDefaultModel();
		model.read(is, null, "TTL");
		List<Resource> leafs = getLeafConcepts(model);
		for (Resource leaf : leafs) {
			//traverseUp(leaf, model, v);
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(VOCABULARY.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		//File file = File.createTempFile("skos", "ntrf");
		File file = new File("test.xml");
		marshaller.marshal(v, file);
		return file;
	}
	
	public File mapToSimpleExcel(InputStream is) throws Exception {
		Workbook wb = new XSSFWorkbook();
	    XSSFSheet sheet = (XSSFSheet) wb.createSheet();		
	    XSSFRow rowHeader = sheet.createRow(0);
	    ArrayList<String> columnNames = new ArrayList<String>();

	    
	    columnNames.add("prefLabel_en");	    
	    columnNames.add("definition_en");
	    columnNames.add("uri");
	    columnNames.add("status");
	    for (int j = 0; j < columnNames.size(); j++) {
	        // create first row
	        XSSFCell cell = rowHeader.createCell(j);
	        cell.setCellValue(columnNames.get(j));
	    }	    
	    
	    Set<String> added = new HashSet<String>();
	    Model model = ModelFactory.createDefaultModel();
		model.read(is, null, "TTL");
		List<Resource> leafs = getLeafConcepts(model);
		for (Resource leaf : leafs) {
			traverseUp(leaf, model, sheet, added);
		}
		File file = new File("test.xlsx");
		FileOutputStream os = new FileOutputStream(file);
		wb.write(os);
		wb.close();
		os.close();
		
		return file;
		
	}

}
