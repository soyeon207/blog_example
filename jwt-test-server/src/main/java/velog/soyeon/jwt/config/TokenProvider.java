package velog.soyeon.jwt.config;

//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
//    private static final String AUTHORITIES_KEY = "auth";
//    private final String secret;
//    private final long expire;
//    private Key key;
//
//    public TokenProvider(
//            @Value("${JWT_SECRET}") String secret,
//            @Value("${JWT_TOKEN}") long expire) {
//        this.secret = secret;
//        this.expire = expire;
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//        // 빈이 생성 되고 주입을 받은 다음에 secret 값을 Base64 Decode 해서 key 변수에 할당
//        byte[] keyBytes = Decoders.BASE64.decode(secret);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String createToken(Authentication authentication) {
//        // Authentication 권한 정보를 이용해서 토큰을 생성
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        long now = (new Date()).getTime();
//        Date validity = new Date(now + this.tokenValidityInMilliseconds);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName()) // 토큰 용도
//                .claim(AUTHORITIES_KEY, authorities) //
//                .signWith(key, SignatureAlgorithm.HS512) // HS512과 Key로 Sign
//                .setExpiration(validity) // 토큰 만료 시간 설정
//                .compact();
//    }
//
//    public Authentication getAuthentication(String token) {
//        // Token 에 담겨 있는 정보를 이용해 Authentication 객체를 리턴하는 메소드
//        Claims claims = Jwts
//                .parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        User principal = new User(claims.getSubject(), "", authorities);
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//    }
//
//    public boolean validateToken(String token) {
//        // 토큰의 유효성 검사
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            logger.info("잘못된 JWT 서명입니다.");
//        } catch (ExpiredJwtException e) {
//            logger.info("만료된 JWT 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            logger.info("지원되지 않는 JWT 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            logger.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
//    }

}
