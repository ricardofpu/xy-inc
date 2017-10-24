package br.com.xy.inc.infrastructure.errors;

import br.com.xy.inc.global.exception.error.ErrorCode;

public class PoiErrors extends ErrorCode {

    public PoiErrors(String code, String key) {
        super(code, key);
    }

    public static final class PoiErrorCode {

        private static final ErrorCode EXISTS_POI_ID = new PoiErrors("EXISTS_POI_ID", "exists.poi.id");
        private static final ErrorCode DELETE_NOT_ALLOWED = new PoiErrors("DELETE_NOT_ALLOWED", "delete.not.allowed");

        static {
            new PoiErrorCode();
        }

        public static ErrorCode getExistsPoiId() {
            return EXISTS_POI_ID;
        }

        public static ErrorCode getDeleteNotAllowed() { return DELETE_NOT_ALLOWED; }
    }

}



