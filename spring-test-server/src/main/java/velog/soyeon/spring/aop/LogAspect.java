package velog.soyeon.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect    // Aspect 명시
@Component // 스프링 빈 등록
public class LogAspect {

    //로거 생성
    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // @Around("@annotation(LogExecutionTime)")
    @Around("execution(* velog.soyeon.spring.aop.*.*(..))") // 메소드 실행전후에 공통 로직을 적용할 때 사용
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();                      // 스탑워치 시작
        Object proceed = joinPoint.proceed();   // 메소드 실행
        stopWatch.stop();                       // 스탑워치 끝
        logger.info(stopWatch.prettyPrint());   // 로깅
        return proceed;
    }

}
