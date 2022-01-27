package velog.soyeon.jwt.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtToken {

    @Value("${JWT_SECRET}")
    private String secret;

    // 로그인 (유효기간 : 일주일 : 7 * 24 * 60 * 60)
    @Value("${JWT_EXPIRE:604800}")
    private long expire;

    // 로그인 갱신 토크 (유효기간 : 한 달 : 30 * 24 * 60 * 60)
    @Value("${JWT_REFRESH_EXPIRE:2592000}")
    private long refreshExpire;

    @Bean
    OnulErpJwtTokenService jwtTokenService() {
        return new OnulErpJwtTokenServiceImpl(this.secret);
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new OnulErpJwtAccessDeniedHandler();
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new OnulErpJwtAuthenticationEntryPoint();
    }

    @Bean
    OnulErpJwtRequestFilter onulApiJwtRequestFilter(OnulErpJwtTokenService jwtTokenService) {
        return new OnulErpJwtRequestFilter(jwtTokenService);
    }

    @Bean
    OnulErpJwtAuthService jwtAuthService(AuthenticationManager authenticationManager, OnulErpJwtTokenService jwtTokenService) {
        return new OnulErpJwtAuthServiceImpl(jwtTokenService, this, authenticationManager);
    }

}
