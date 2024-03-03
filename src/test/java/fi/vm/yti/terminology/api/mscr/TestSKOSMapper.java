package fi.vm.yti.terminology.api.mscr;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.jupiter.api.Test;

class TestSKOSMapper {

	
	@Test
	void test() throws Exception {
		SKOSMapper m = new SKOSMapper();
		InputStream is = getClass().getClassLoader().getResourceAsStream("importapi/skos/data-stewardship-terminology.ttl");
		m.mapToSimpleExcel(is);
		
	}

	
	@Test
	void testSKOSGeneration() {
		SKOSMapper m = new SKOSMapper();
		Model model = ModelFactory.createDefaultModel();
		InputStream is = getClass().getClassLoader().getResourceAsStream("exportapi/clarin1-termed.ttl");
		model.read(is, null, "TTL");
		Model r = m.mapTermedToSKOS(model);
		r.write(System.out, "TTL");
	}
}
