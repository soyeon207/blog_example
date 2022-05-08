package velog.soyeon.spring.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean firstCustomFilterBean() {
        FilterRegistrationBean firstRegistrationBean = new FilterRegistrationBean(new FirstCustomFilter());
        firstRegistrationBean.setOrder(1);
        return firstRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean secondCustomFilterBean() {
        FilterRegistrationBean secondRegistrationBean = new FilterRegistrationBean(new SecondCustomFilter());
        secondRegistrationBean.setOrder(2);
        return secondRegistrationBean;
    }

}
