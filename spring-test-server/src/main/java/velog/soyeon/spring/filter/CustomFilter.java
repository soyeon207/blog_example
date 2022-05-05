package velog.soyeon.spring.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("1. CustomFilter 실행");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info(">>>>>>>>> 2. CustomFilter 동작 >>>>>>>>>");
        log.info(">>>>>>>>> 시작 >>>>>>>>>");
        chain.doFilter(request, response);
        log.info(">>>>>>>>> 종료 >>>>>>>>>");
    }

    @Override
    public void destroy() {
        log.info("3. CustomFilter 삭제");
        Filter.super.destroy();
    }
}
