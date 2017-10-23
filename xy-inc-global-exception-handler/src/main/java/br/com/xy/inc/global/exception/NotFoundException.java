package br.com.xy.inc.global.exception;

import br.com.xy.inc.global.exception.error.ResourceValue;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class NotFoundException extends RuntimeException {

    private ResourceValue resource;

    public NotFoundException() {
    }

    public NotFoundException(ResourceValue resource, Throwable cause) throws RuntimeException {
        super(tryBuildLogMessageAsJson(resource), cause);
        this.resource = resource;
    }

    public NotFoundException(ResourceValue resource) throws RuntimeException {
        super(tryBuildLogMessageAsJson(resource));
        this.resource = resource;
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceValue getResource() {
        return this.resource;
    }

    private static String tryBuildLogMessageAsJson(Object object) {
        try {
            return (new ObjectMapper()).writeValueAsString(object);
        } catch (Exception var2) {
            return null;
        }
    }

}
