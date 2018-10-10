package fi.vm.yti.terminology.api.migration.task;

import fi.vm.yti.migration.MigrationTask;
import fi.vm.yti.terminology.api.migration.MigrationService;
import fi.vm.yti.terminology.api.migration.PropertyUtil;
import fi.vm.yti.terminology.api.model.termed.MetaNode;
import fi.vm.yti.terminology.api.model.termed.ReferenceMeta;
import fi.vm.yti.terminology.api.model.termed.VocabularyNodeType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Migration for YTI-1106, classification->information domain and HomographNumber fix."
 */
@Component
public class V8_UpdateGroupInformationDomain implements MigrationTask {

    private final MigrationService migrationService;

    V8_UpdateGroupInformationDomain(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    @Override
    public void migrate() {
        migrationService.updateTypes(VocabularyNodeType.TerminologicalVocabulary, meta -> {
            // Go  through text-attributes and add descriptions to known ids
            updateMeta(meta);
        });
    }

    /**
     * Update MetaNodes text- and reference-attributes with given description texts.
     * @param meta MetaNode to be updated
     * @return true if updated
     */
    boolean updateMeta(MetaNode meta){
        boolean rv = false;;
        // Print textAttributes

        String domainName=meta.getDomain().getId().name();
        if(domainName.equals("TerminologicalVocabulary")){
            // ReferenceAttributes
            updateTextAttributeGroup(meta,"inGroup","Tietoalue","Information domain");
        }

        return rv;
    }


    /**
     * Update Group reference-attribute labels
     * @param meta
     * @param attributeName
     * @return
     */
    boolean updateTextAttributeGroup(MetaNode meta, String attributeName,  String fival, String enval){
        boolean rv = false;
        List<ReferenceMeta> ra = meta.getReferenceAttributes().stream().filter(item -> item.getId().equals(attributeName)).collect(Collectors.toList());
        if(!ra.isEmpty()){
            if(ra.size()>1){
                System.err.println("Error, several "+ attributeName+" attributes in same node ");
            } else {
               ReferenceMeta att = ra.get(0);
                // check if description exist and add new one
                att.updateProperties(PropertyUtil.merge(att.getProperties(),PropertyUtil.prefLabel(fival,enval)));
                rv = true;
            }
        }
        return rv;
    }

}
