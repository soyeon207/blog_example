package velog.soyeon.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

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
    String adminView() {
        return "admin";
    }

    @RequestMapping("/my")
    String myView() {
        return "my";
    }

    @RequestMapping("/signup")
    String signupView() {
        return "signup";
    }



}
