package velog.soyeon.spring.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        log.info("[preHandle] authorizationHeader>>>>>>>>>>>>>>>>>>"+authorizationHeader);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("[postHandler] 컨트롤러 응답 >>>>>>>>>>>>>>>>>>>>>>>>>>>..");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[afterCompletion] 컨트롤러 응답 완료 >>>>>>>>>>>>>>.");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
