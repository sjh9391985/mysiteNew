package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object aroungAdvice(ProceedingJoinPoint pjp) throws Throwable{
		
		// before
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();
		
		
		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		//class name
		String className = pjp.getTarget().getClass().getName();
		
		// method name
		String methodName = pjp.getSignature().getName();
		
		// class+method
		String taskName = className +"."+ methodName;
		
		System.out.println("[Execution Time][" + taskName + "] " + totalTime + "millis");
		
		return result;
		
	}
	
}
