package br.com.xyinc.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceValue {
    private final String resource;
    private final String value;

    public ResourceValue(Class classs, String value) {
        this.resource = classs.getSimpleName();
        this.value = value;
    }

    public ResourceValue(String resource, String value) {
        this.resource = resource;
        this.value = value;
    }

    public String toString() {
        return "ResourceValue[resource='" + this.resource + "', value='" + this.value + '\'' + '}';
    }

    public String getResource() {
        return this.resource;
    }

    public String getValue() {
        return this.value;
    }
}
