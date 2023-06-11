package org.boardpj.models.board;

import org.boardpj.commons.CommonException;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.HttpStatus;

public class GuestPasswordIncorrectException extends CommonException {

    public GuestPasswordIncorrectException() {
        super(bundleValidation.getString("GuestPw.incorrect"), HttpStatus.BAD_REQUEST);
    }
}
