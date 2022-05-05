package velog.soyeon.spring.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter/test")
public class FilterTestController {

    @GetMapping
    public String test() {
        return "test";
    }

}
