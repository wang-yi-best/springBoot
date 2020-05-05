package com.wang.springboot.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>
 * Title: AspectController
 * </p>
 * <p>
 * Description:
 * </p>
 * @author yi.wang @date 2020年4月30日 下午1:45:48 @version 1.0
 */
@Aspect
@Component
public class AspectController {

	private final static Logger LOGGER = LoggerFactory.getLogger(AspectController.class);

	// 关联在方法上的切点，第一个*代表返回类型不限，第二个*代表module下所有子包，第三个*代表所有类，第四个*代表所有方法，(..) 代表参数不限
	@Pointcut("execution(public * com.wang.springboot.modules.*.controller.*.*(..))")
	@Order(1) // 代表优先级，数字越小优先级越高
	public void controllerPointCut() {

	}

	@Before(value = "com.wang.springboot.aspect.AspectController.controllerPointCut()")
	public void beforCOntroller(JoinPoint joinPoint) {
		LOGGER.debug("Before controller");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		LOGGER.debug("请求来源：" + request.getRemoteAddr());
		LOGGER.debug("请求URL：" + request.getRequestURL().toString());
		LOGGER.debug("请求方式：" + request.getMethod());
		LOGGER.debug(
				"响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));
	}

	/**
	 * -环绕通知 = 前置 + 目标方法执行 + 后置通知 ProceedingJoinPoint 继承了 JoinPoint，是在 JoinPoint
	 * 的基础上暴露出 proceed 这个方法， -这个是 aop 代理链执行的方法，
	 * 执行proceed方法的作用是让目标方法执行，这也是环绕通知和前置、后置通知方法的一个最大区别;
	 */
	@Around(value = "com.wang.springboot.aspect.AspectController.controllerPointCut()")
	public Object aroundCOntroller(ProceedingJoinPoint pJoinPoint) {

		return null;
	}

	@After(value = "com.wang.springboot.aspect.AspectController.controllerPointCut()")
	public void afterCOntroller(JoinPoint joinPoint) {

	}
}
