package velog.soyeon.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velog.soyeon.jwt.config.JwtConfig;
import velog.soyeon.jwt.dto.UserDTO;
import velog.soyeon.jwt.dto.UserRequest;
import velog.soyeon.jwt.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserServiceImpl usersService;
    private final JwtConfig jwtConfig;

    @PostMapping("/signup")
    public UserDTO createUser(UserRequest userRequest) {
        return usersService.createUser(userRequest);
    }

    @GetMapping("/my")
    public UserDTO findUser(Authentication authentication) {
        if (authentication == null) {
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.");
        }
        return usersService.findUser(authentication.getName());
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public List<UserDTO> findAllUser() {
        return usersService.findAll();
    }

    @PostMapping("/login")
    public String login(UserRequest userRequest) {
        UserDTO users = usersService.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassWord());
        return jwtConfig.createToken(users.getEmail(), Arrays.asList(users.getUserRole().getValue()));
    }

}
