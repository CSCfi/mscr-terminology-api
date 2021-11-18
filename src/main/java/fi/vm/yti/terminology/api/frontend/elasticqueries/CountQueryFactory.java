package fi.vm.yti.terminology.api.frontend.elasticqueries;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.yti.terminology.api.frontend.searchdto.CountDTO;
import fi.vm.yti.terminology.api.frontend.searchdto.CountSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CountQueryFactory {
    private static final Logger log = LoggerFactory.getLogger(CountQueryFactory.class);

    private final ObjectMapper objectMapper;

    public CountQueryFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SearchRequest createQuery() {
        QueryBuilder withIncompleteHandling = QueryBuilders.boolQuery()
                .mustNot(QueryBuilders.matchQuery("properties.status.value", "INCOMPLETE"));

        SearchRequest sr = new SearchRequest("concepts,vocabularies")
                .source(new SearchSourceBuilder()
                        .size(0)
                        .query(withIncompleteHandling)
                        .aggregation(this.createStatusAggregation())
                        .aggregation(this.createGroupAggregation())
                        .aggregation(this.createIndexAggregation()));

        log.debug("Count request: " + sr.toString());
        return sr;
    }

    private TermsAggregationBuilder createStatusAggregation() {
        String scriptSource = "doc.containsKey('status') ? doc.status : params._source.properties.status[0].value";
        Map<String, Object> params = new HashMap<>(16);
        Script script = new Script(
                Script.DEFAULT_SCRIPT_TYPE,
                "painless",
                scriptSource,
                params);

        return AggregationBuilders
                .terms("statusagg")
                .size(300)
                .script(script);
    }

    private TermsAggregationBuilder createIndexAggregation() {
        return AggregationBuilders
                .terms("indexagg")
                .size(300)
                .field("_index");
    }

    private TermsAggregationBuilder createGroupAggregation() {
        String scriptSource = String.join("\n",
                "if (!params._source.containsKey('references')) {",
                "    return null;",
                "}",
                "   return params._source.references.inGroup",
                "       .stream()",
                "       .map(x -> x.id)",
                "       .collect(Collectors.toList())");

        Map<String, Object> params = new HashMap<>(16);
        Script script = new Script(
                Script.DEFAULT_SCRIPT_TYPE,
                "painless",
                scriptSource,
                params);

        return AggregationBuilders
                .terms("groupagg")
                .size(300)
                .script(script);
    }

    public CountSearchResponse parseResponse(SearchResponse response) {
        CountSearchResponse ret = new CountSearchResponse();
        ret.setTotalHitCount(response.getHits().getTotalHits());

        Terms indexAgg = response.getAggregations().get("indexagg");
        var indices = indexAgg
                .getBuckets()
                .stream()
                .collect(Collectors.toMap(
                        MultiBucketsAggregation.Bucket::getKeyAsString,
                        MultiBucketsAggregation.Bucket::getDocCount));

        Terms statusAgg = response.getAggregations().get("statusagg");
        var statuses = statusAgg
                .getBuckets()
                .stream()
                .collect(Collectors.toMap(
                        MultiBucketsAggregation.Bucket::getKeyAsString,
                        MultiBucketsAggregation.Bucket::getDocCount));

        Terms groupAgg = response.getAggregations().get("groupagg");
        var groups = groupAgg
                .getBuckets()
                .stream()
                .collect(Collectors.toMap(
                        MultiBucketsAggregation.Bucket::getKeyAsString,
                        MultiBucketsAggregation.Bucket::getDocCount));

        ret.setCounts(new CountDTO(indices, statuses, groups));

        return ret;
    }
}
