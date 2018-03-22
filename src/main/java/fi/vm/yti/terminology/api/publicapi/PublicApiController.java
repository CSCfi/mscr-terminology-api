package fi.vm.yti.terminology.api.publicapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/publicapi")
public class PublicApiController {

    private final PublicApiTermedService termedService;

    public PublicApiController(PublicApiTermedService termedService) {
        this.termedService = termedService;
    }

    @RequestMapping(value = "/vocabularies", method = GET, produces = APPLICATION_JSON_VALUE)
    List<PublicApiVocabulary> getVocabularyList() {
        return termedService.getVocabularyList();
    }
}