package org.boardpj.models.board.config;

import org.boardpj.commons.CommonException;
import org.springframework.http.HttpStatus;

public class BoardConfigNotExistException extends CommonException {
    public BoardConfigNotExistException(){
    super(bundleValidation.getString("Validation.board.notExists"), HttpStatus.BAD_REQUEST);
}
}
