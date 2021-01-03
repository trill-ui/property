package com.learn.aop;

import com.alibaba.fastjson.JSON;
import com.learn.annotation.SysLog;
import com.learn.entity.SysLogEntity;
import com.learn.service.SysLogService;
import com.learn.utils.HttpContextUtils;
import com.learn.utils.IPUtils;
import com.learn.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统日志，切面处理类
 * 
 */
//利用@Aspect声明一个切面。
@Aspect
@Component
public class SysLogAspect {
	//注入Service  
	@Autowired
	private SysLogService sysLogService;
	//定义切入点 
	@Pointcut("@annotation(com.learn.annotation.SysLog)")
	public void logPointCut() { 
		System.out.println("切入点...");
	}
	/**  
	 * 前置通知 用于保存系统日志  
	 *  
	 * @param joinPoint 切点  
	 */  
	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("方法规则式拦截 " + method.getName());
		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);//获取注解
		if(syslog != null){
			//注解上的描述 
			sysLog.setOperation(syslog.value());
		}
		
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		
		//请求的参数
		Object[] args = joinPoint.getArgs();
		String params = JSON.toJSONString(args[0]);
		sysLog.setParams(params);
		
		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));
		
		//用户名
		String username = ShiroUtils.getUserEntity().getUsername();
		sysLog.setUsername(username);
		
		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.save(sysLog);
	}
	
}
