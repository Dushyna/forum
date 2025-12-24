package ait.cohort70.forum.service.loging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "ait.cohort70.forum.service.PostServiceImpl")
@Aspect
public class PostServiceLogger {
    @Pointcut("execution(public * ait.cohort70.forum.service.PostServiceImpl.*(Long)) && args(id)")
    public void findById(Long id){

    }

    @Pointcut("@annotation(ait.cohort70.forum.service.loging.PostLogger)")
    public void annotatedPostLogger(){}

    @Pointcut("execution(public Iterable<ait.cohort70.forum.dto.PostDto> ait.cohort70.forum.service.PostServiceImpl.findPosts*(..))")
    public void bulkingFindPosts(){

    }

    @Before("findById(id)")
    public void logRequestPostById(Long id){
        log.info("Request post with id: {}", id);
    }

    @AfterReturning("annotatedPostLogger()")
    public void logAnnotatedPostLogger(JoinPoint joinPoint){
        log.info("Annotated by PostLogger method {}, done", joinPoint.getSignature().getName());
    }

    @Around("bulkingFindPosts()")
    public Object logBulkingFindPosts(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof String str){
                args[i]= str.toLowerCase();
            }

        }
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(args);
        long endTime = System.currentTimeMillis();
        log.info("BMethod {}, time {} ms", joinPoint.getSignature().getName(), endTime - startTime);
        return result;

    }
}
