package velog.soyeon.security.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velog.soyeon.security.UserRepository;
import velog.soyeon.security.Users;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    @GetMapping
    public List<Users> gg() {
        return userRepository.findAll();
    }

    @GetMapping("/success")
    public List<Users> ss() {
        return userRepository.findAll();
    }

    @GetMapping("/fail")
    public String fail() {
        return "실패! ";
    }





}
