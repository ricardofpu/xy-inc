package br.com.xy.inc.global.exception;

import br.com.xy.inc.global.exception.error.ResourceValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class NotFoundExceptionTest {

    class Poi {}

    @Test
    public void throwNotFoundExceptionWithoutResource() {
        try {
            throw new NotFoundException();
        } catch (NotFoundException e) {
            Assert.assertNotNull(e);
            Assert.assertNull(e.getResource());
        }
    }

    @Test
    public void throwNotFoundExceptionWithResource() {
        String id = UUID.randomUUID().toString();
        try {
            throw new NotFoundException(new ResourceValue(Poi.class, id));
        } catch (NotFoundException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(e.getResource().getValue(), id);
            Assert.assertEquals(e.getResource().getResource(), Poi.class.getSimpleName());
        }
    }
}
