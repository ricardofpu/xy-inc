package br.com.xy.inc.global.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceValueResponse {

    private final String resource;
    private final String value;

    public ResourceValueResponse(ResourceValue resource) {
        this.resource = resource.getResource();
        this.value = resource.getValue();
    }

    public String getResource() {
        return this.resource;
    }

    public String getValue() {
        return this.value;
    }

}
