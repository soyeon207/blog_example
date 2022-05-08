package velog.soyeon.spring.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    @GetMapping
    public boolean getTest() {
        return true;
    }

}
