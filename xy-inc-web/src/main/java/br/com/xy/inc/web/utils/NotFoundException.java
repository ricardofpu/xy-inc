package br.com.xy.inc.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8741620759618279545L;
    private static final Logger LOG = LogManager.getLogger(NotFoundException.class);
    private ResourceValue resource;

    public NotFoundException() {
    }

    public NotFoundException(ResourceValue resource) throws RuntimeException {
        super(tryBuildLogMessageAsJson(resource));
        Assert.notNull(resource, "resource can't be null");
        this.resource = resource;
    }

    public NotFoundException(ResourceValue resource, Throwable cause) throws RuntimeException {
        super(tryBuildLogMessageAsJson(resource), cause);
        Assert.notNull(resource, "resource can't be null");
        this.resource = resource;
    }

    public ResourceValue getResource() {
        return this.resource;
    }

    private static String tryBuildLogMessageAsJson(Object object) {
        try {
            return (new ObjectMapper()).writeValueAsString(object);
        } catch (Exception var2) {
            LOG.warn("error trying parse {} to json format", object);
            return null;
        }
    }
}
