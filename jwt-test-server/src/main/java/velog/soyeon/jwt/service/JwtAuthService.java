package velog.soyeon.jwt.service;

public interface JwtAuthService {

    OnulErpJwtAuthenticationToken login(String userEmail) throws Exception;
    OnulErpJwtAuthenticationToken refreshToken(Authentication authentication) throws Exception;
    void logoutSuccess(HttpSession httpSession);

}
