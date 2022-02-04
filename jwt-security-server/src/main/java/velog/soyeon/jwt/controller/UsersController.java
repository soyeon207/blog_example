package velog.soyeon.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velog.soyeon.jwt.config.JwtConfig;
import velog.soyeon.jwt.dto.UserRequest;
import velog.soyeon.jwt.entity.Users;
import velog.soyeon.jwt.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserServiceImpl usersService;
    private final JwtConfig jwtConfig;

    @PostMapping("/signup")
    public void createUser(UserRequest userRequest, HttpServletResponse response) throws IOException {
        usersService.createUser(userRequest);
        response.sendRedirect("/login");
    }

    @GetMapping("/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        response.sendRedirect("/login");
    }

    @GetMapping("/login")
    public String  login(UserRequest userRequest, HttpServletResponse response) {
        Users users = usersService.findUser(userRequest);
        return jwtConfig.createToken(users.getEmail(), Arrays.asList(users.getUserRole().getValue()));
    }


}
