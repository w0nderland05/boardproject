package org.boardpj.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.boardpj.commons.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.boardpj.controllers")
public class CommonController {
    @ExceptionHandler
    public String errorHandler(Exception e, Model model, HttpServletResponse response){
        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if(e instanceof CommonException){
          CommonException commonException = (CommonException) e;
          status = commonException.getStatus().value();

        }

        response.setStatus(status);

        model.addAttribute("status", status);
        model.addAttribute("message", e.getMessage());
        model.addAttribute("exception", e);

        return "error/common";



    }
}