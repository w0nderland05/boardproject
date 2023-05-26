package org.boardpj.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler { //실패시 세부사항 정보
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        session.setAttribute("userId", userId);
        try{
            if(userId == null || !userId.isBlank()){
                throw  new LoginValidationException("userId","NotBlank.userId");
            }
            if(userPw == null || !userPw.isBlank()){
                throw new LoginValidationException("userPw", "NovBlank.userPw");
            }
            throw new LoginValidationException("global", "Validation.login.fail");

        }catch (LoginValidationException e){
            session.setAttribute(e.getField(), exception.getMessage());
        }

        response.sendRedirect(request.getContextPath() +"/member/login");



    }
}
