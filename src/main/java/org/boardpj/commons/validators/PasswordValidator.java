package org.boardpj.commons.validators;

public interface PasswordValidator {
    /**
     * 비밀번호 형식 체크 - 알파벳 체크
     * @param password
     * @param caseIncentive
     *          false:소문자 +대문자 반드시 포함되는 패턴
     *          true: 대소문자 상관없이 포함되는 패턴
     * @return
     */
    default boolean alphaCheck(String password, boolean caseIncentive){
        if(caseIncentive){ //대소문자 구분없이 체크
            return password.matches("[a-zA-Z]+"); //+한자이상 포함

        }

        //대문자, 소문자 각각 체크
        return password.matches("[a-z]+ && [A-Z]+");
    }

    /**
     * 숫자가 포함된 패턴인지 체크
     * @param password
     * @return
     */
    default boolean numCheck(String password){
        return password.matches("\\d+");//[0-9]
    }

    /**
     * 특수문자가 포함된 패턴인지 체크
     * @param password
     * @return
     */
    default boolean specialCharsCheck(String password){
        return password.matches("[~!#$%\\^&\\*()-_+=]+");
    }
}
