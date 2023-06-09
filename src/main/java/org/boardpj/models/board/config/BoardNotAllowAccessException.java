package org.boardpj.models.board.config;

import org.boardpj.commons.CommonException;
import org.boardpj.entities.Board;
import org.springframework.http.HttpStatus;

public class BoardNotAllowAccessException extends CommonException{

    public BoardNotAllowAccessException() {
        super(bundleValidation.getString("Validation.board.NotAllowAccess"), HttpStatus.UNAUTHORIZED);
    }


}
