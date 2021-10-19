package com.test.sampleAOP.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SampleAdvice {
	

	
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	/**
	 *  	@GetMapping import org.springframework.web.bind.annotation.GetMapping
	 *  	getMapping일때 적용시키도록 포인트컷 설정(어디에)
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void GetMapping() {
		
	}
	/*
	 * @Pointcut("@annotation(com.test.sampleAOP.customAnnotation.MyCustom)")
	 * public void CustomAnnotation() {
	 * 
	 * }
	 */
	
	//@Before("GetMapping()")
	@Before("@annotation(com.test.sampleAOP.customAnnotation.MyCustom)")
	public void before(JoinPoint joinPoint) {
		logger.info("=====================AspectJ TEST  : Before Logging Start=====================");
		logger.info("=====================AspectJ TEST  : Before Logging End=====================");
	}
	
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    //@AfterReturning("CustomAnnotation()")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        logger.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }
    
    @After("GetMapping()")
    //@After("CustomAnnotation()")
    public void after(JoinPoint joinPoint) {
    	logger.info("=====================AspectJ TEST  : After Logging Start=====================");
    	logger.info("=====================AspectJ TEST  : After Logging END=====================");
    }
    @Around("GetMapping()")
    //@Around("CustomAnnotation()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("=====================AspectJ TEST  : Around Logging Start=====================");
        try {
            Object result = joinPoint.proceed();
            logger.info("=====================AspectJ TEST  : Around Logging END=====================");
            return result;
        }catch (Exception e) {
            logger.error("=====================AspectJ Around Exception=====================");
            logger.error(e.toString());
            return null;
        }
    }
    @Around("@annotation(com.test.sampleAOP.customAnnotation.LogExecuteTime)")
    public Object AroundForTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("=====================AspectJ TEST  : AroundForTimeLog Logging Start=====================");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        logger.info("=====================AspectJ TEST  : AroundForTimeLog Logging END=====================");
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return result;
    }

}
