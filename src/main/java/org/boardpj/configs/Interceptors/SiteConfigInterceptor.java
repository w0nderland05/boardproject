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
@Component("siteConfig")
@RequiredArgsConstructor
public class SiteConfigInterceptor implements HandlerInterceptor {
    private final ConfigInfoService infoService;
    private final HttpServletRequest request;


    //요청처리전 공통기능 추가, 접근제한 기능
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**사이트 설정조회 */
        Map<String, String> siteConfigs = infoService.get("siteConfig", new
                TypeReference<Map<String, String>>(){});
        request.setAttribute("siteConfig", siteConfigs);
        return true;
    }
    public String get(String name) {
        Map<String, String> siteConfig = (Map<String, String>)request.getAttribute("siteConfig");
        String value = siteConfig == null ? "" : siteConfig.get(name);

        return value;
    }

}
