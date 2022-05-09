package velog.soyeon.spring.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)             // 해당 어노테이션을 어디에서 사용할 수 있을지 결정
@Retention(RetentionPolicy.RUNTIME)     // 어노테이션 정보 유지 위치
public @interface LogExecutionTime {
}
