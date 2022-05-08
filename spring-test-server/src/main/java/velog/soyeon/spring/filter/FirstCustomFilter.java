package velog.soyeon.spring.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Slf4j
public class FirstCustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[First] 1. FirstCustomFilter 실행");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("[First] >>>>>>>>> 2. FirstCustomFilter 동작 >>>>>>>>>");
        log.info("[First] >>>>>>>>> 시작 >>>>>>>>>");
        chain.doFilter(request, response);
        log.info("[First] >>>>>>>>> 종료 >>>>>>>>>");
    }

    @Override
    public void destroy() {
        log.info("[First] 3. FirstCustomFilter 삭제");
        Filter.super.destroy();
    }
}
