package velog.soyeon.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 인증이 성공한 경우 아래 로직 수행
        // SecurityContextHolder > SecurityContext 에 Authentication 을 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // / 페이지로 redirect 해준다.
        response.sendRedirect("/login/success");
    }

}
