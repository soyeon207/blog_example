package velog.soyeon.spring.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {

//    @LogExecutionTime
    @GetMapping("/test")
    public boolean test() throws InterruptedException {
        Thread.sleep(40);
        return true;
    }

}
