package org.boardpj.commons.validators;

public interface MobileValidator {
    default boolean mobileNumCheck(String mobile){
        /**
         * 1.형식의 통일화 (숫자만 남기기)
         * 2.패턴생성 체크
         */

        mobile = mobile.replaceAll("\\D", "");
        String pattern = "^01[016]\\d{3,4}\\d{4}$";

        return false;
    }
}
