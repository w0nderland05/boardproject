package org.boardpj.configs.Interceptors;


import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.boardpj.commons.configs.ConfigInfoService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 사이트 설정 유지
 */
@Component
@RequiredArgsConstructor
public class SiteConfigInterceptor implements HandlerInterceptor {
    private final ConfigInfoService infoService;


    //요청처리전 공통기능 추가, 접근제한 기능
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**사이트 설정조회 */
        Map<String, String> siteConfigs = infoService.get("siteConfig", new TypeReference<Map<String, String>>(){});
       request.setAttribute("cssJsVersion",1); //관리자에서 버전바꾸면 자동으로 바뀌도록

        return true;
    }
}
