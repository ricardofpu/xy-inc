package br.com.xy.inc.global.exception;

import br.com.xy.inc.global.exception.error.ErrorCode;
import org.junit.Assert;
import org.junit.Test;

public class BusinessExceptionTest {

    @Test
    public void throwBusinessExceptionWithoutErrorCode() {
        try {
            throw new BusinessException();
        } catch (BusinessException e) {
            Assert.assertNotNull(e);
            Assert.assertNull(e.getErrorCode());
        }
    }

    @Test
    public void throwBusinessExceptionWithErrorCode() {
        try {
            throw new BusinessException(new ErrorCode("exists.id", getBusinessMessage()));
        } catch (BusinessException e) {
            Assert.assertNotNull(e);
            Assert.assertNotNull(e.getErrorCode());
            Assert.assertEquals(e.getErrorCode().getMessage(), getBusinessMessage());
        }
    }

    private String getBusinessMessage() { return "This id already exists."; }
}
