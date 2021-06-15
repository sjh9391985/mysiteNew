package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) //method에만 붙이겠다
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	// String value() default "";
	
	public String role() default "USER";
	
	public boolean test() default false;
}
