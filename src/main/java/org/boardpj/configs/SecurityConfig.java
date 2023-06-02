package org.boardpj.configs;

import jakarta.servlet.http.HttpServletResponse;
import org.boardpj.models.member.LoginFailureHandler;
import org.boardpj.models.member.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/login");

        http.authorizeHttpRequests() //url패턴에 따라 접근권한 설정
                .requestMatchers("/mypage/**").authenticated()    //회원전용 url
               // .requestMatchers("/admin/**").hasAuthority("ADMIN") //관리자 전용
                .anyRequest().permitAll(); //그외 모든 페이지는 모든 회원이 접근가능

        http.exceptionHandling()
                .authenticationEntryPoint((req, res, e) -> {
                    String URI = req.getRequestURI();

                    if (URI.indexOf("/admin") != -1) { // 관리자 페이지
                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT AUTHORIZED");
                    } else { // 회원 전용 페이지
                        String redirectURL = req.getContextPath() + "/member/login";
                        res.sendRedirect(redirectURL);
                    }
                });

        http.headers().frameOptions().sameOrigin();//같은 도메인내에서만 통신가능하도록
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {


        return new BCryptPasswordEncoder();
    }

}
