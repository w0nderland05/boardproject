package org.boardpj.configs.Interceptors;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 사이트 설정 유지
 */
@Component
public class SiteConfigInterceptor implements HandlerInterceptor {

    //요청처리전 공통기능 추가, 접근제한 기능
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       request.setAttribute("cssJsVersion",1); //관리자에서 버전바꾸면 자동으로 바뀌도록

        return true;
    }
}
