package velog.soyeon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import velog.soyeon.security.handler.CustomLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 정적 자원에 대해서는 Security 설정을 적용하지 않음.

    @Override
    public void configure(WebSecurity web) {
        // h2-console 은 인증하지 않고 접속할 수 있도록 설정
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login").authenticated() // /about 요청에 대해서는 로그인을 요구함
                .antMatchers("/admin/**").hasRole("ADMIN") // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
                .anyRequest()
                .permitAll().and()// 나머지 요청에 대해서는 로그인을 요구하지 않음
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // UsernamePasswordAuthenticationFilter 전에 적용되도록 설정
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/login/success");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler()); // successHandler 에 기존에 만들어둔 hanlder 설정
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }
}
