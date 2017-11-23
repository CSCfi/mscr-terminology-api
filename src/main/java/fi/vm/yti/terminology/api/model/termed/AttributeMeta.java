package fi.vm.yti.terminology.api.model.termed;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Collections.emptyMap;

public final class AttributeMeta {

    private final String regex;
    private final String id;
    private final String uri;
    private final Long index;
    private final TypeId domain;
    private final Map<String, List<Permission>> permissions;
    private final Map<String, List<Property>> properties;

    // Jackson constructor
    private AttributeMeta() {
        this("", "", "", 0L, TypeId.placeholder(), emptyMap(), emptyMap());
    }

    public AttributeMeta(String regex,
                         String id,
                         String uri,
                         Long index,
                         TypeId domain,
                         Map<String, List<Permission>> permissions,
                         Map<String, List<Property>> properties) {
        this.regex = regex;
        this.id = id;
        this.uri = uri;
        this.index = index;
        this.domain = domain;
        this.permissions = permissions;
        this.properties = properties;
    }


    public String getRegex() {
        return regex;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public Long getIndex() {
        return index;
    }

    public TypeId getDomain() {
        return domain;
    }

    public Map<String, List<Permission>> getPermissions() {
        return permissions;
    }

    public Map<String, List<Property>> getProperties() {
        return properties;
    }

    public AttributeMeta copyToGraph(UUID graphId) {

        TypeId newDomain = domain.copyToGraph(graphId);

        return new AttributeMeta(regex, id, uri, index, newDomain, permissions, properties);
    }
}