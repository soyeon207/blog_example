package velog.soyeon.spring.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Slf4j
public class SecondCustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[Second] 1. SecondCustomFilter 실행");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("[Second] >>>>>>>>> 2. SecondCustomFilter 동작 >>>>>>>>>");
        log.info("[Second] >>>>>>>>> 시작 >>>>>>>>>");
        chain.doFilter(request, response);
        log.info("[Second] >>>>>>>>> 종료 >>>>>>>>>");
    }

    @Override
    public void destroy() {
        log.info("[Second] 3. SecondCustomFilter 삭제");
        Filter.super.destroy();
    }

}
