package spring.vue.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAOP {

    @Pointcut("execution(* spring.vue.demo.service..*(..))")
    public void logging() {
    }

    @Before("logging()")
    public void beforeLog(){
//        log.info("여기 호출");
    }

}
