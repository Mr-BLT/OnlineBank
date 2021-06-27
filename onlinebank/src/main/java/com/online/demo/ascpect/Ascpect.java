package com.online.demo.ascpect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Ascpect {

  @Pointcut("execution(public * com.online.demo.controller.*.*(..))")
  public void callMethod() {
  }

  @After("callMethod()")
  public void afterMethod() {
    System.err.println(124);
  }

}
