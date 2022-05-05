package velog.soyeon.spring.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean customFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CustomFilter());
        return registrationBean;
    }

}
