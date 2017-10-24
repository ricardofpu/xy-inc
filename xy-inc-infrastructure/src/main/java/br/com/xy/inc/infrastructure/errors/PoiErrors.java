package br.com.xy.inc.infrastructure.errors;

import br.com.xy.inc.global.exception.error.ErrorCode;

public final class PoiErrors extends ErrorCode {

    public PoiErrors(String code, String key) {
        super(code, key);
    }

    public static PoiErrors of(String code, String key) {
        return new PoiErrors(code, key);
    }

    public static final class PoiErrorCode {

        private static final ErrorCode EXISTS_POI_ID = PoiErrors.of("EXISTS_POI_ID", "Poi not saved because exists an poi in database with this id");
        private static final ErrorCode DELETE_NOT_ALLOWED = PoiErrors.of("DELETE_NOT_ALLOWED", "Delete cannot be executed");

        static {
            new PoiErrorCode();
        }

        public static ErrorCode getExistsPoiId() {
            return EXISTS_POI_ID;
        }

        public static ErrorCode getDeleteNotAllowed() {
            return DELETE_NOT_ALLOWED;
        }
    }

}



