package velog.soyeon.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import velog.soyeon.security.repository.UserRepository;
import velog.soyeon.security.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final UserRepository userRepository;


    @RequestMapping("/login")
    String loginView() {
        return "login";
    }

    @RequestMapping("/success")
    String successView() {
        return "success";
    }

    @RequestMapping("/fail")
    String failView() {
        return "fail";
    }

    @RequestMapping("/admin")
    ModelAndView adminView() {
        List<UserDTO> userDTOList = userRepository.findAll().stream().map(u -> UserDTO.builder().id(u.getId()).email(u.getEmail()).password(u.getPassword()).userRole(u.getUserRole()).build()).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView("/admin");
        modelAndView.addObject("userList", userDTOList);
        return modelAndView;
    }

    @RequestMapping("/my")
    ModelAndView myView(Authentication authentication) {
        UserDTO userDTO = Optional.ofNullable(userRepository.findByEmail(authentication.getName()))
                .map(u -> UserDTO.builder().id(u.getId()).email(u.getEmail()).password(u.getPassword()).userRole(u.getUserRole()).build())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        ModelAndView modelAndView = new ModelAndView("/my");
        modelAndView.addObject("userDTO", userDTO);

        return modelAndView;
    }

    @RequestMapping("/signup")
    String signupView() {
        return "signup";
    }


}
