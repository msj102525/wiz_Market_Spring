package com.jyes.www.aspcet.sample;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class SampleAspect {

	@Pointcut("execution(public * com.jyes.www.controller.sample.*Controller.*Data(..))")
    private void ajaxMethod(){}

//	@Around(value = "sampleHomeControllerMethod() || bbsMethod()  || commonMethod() || onmAdministratorMethod() || onmMembershipMethod() || onmReportMethod()")
	@Around(value = "ajaxMethod()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("#### LoginAspect 시작 ####");
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        System.out.println("methodName:"+methodName);
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		for (Object o : joinPoint.getArgs()) {
			if (o instanceof HttpServletRequest) {
				request = (HttpServletRequest) o;
			}
			if (o instanceof HttpServletResponse) {
				response = (HttpServletResponse) o;
			}
		}
		try {
			HttpSession session = request.getSession();
			String loginId = (String) session.getAttribute("loginId");
			String userEnterType = (String) session.getAttribute("UserEnterType");
			System.out.println("### Margo ==> loginId : " + loginId);
			if (loginId == null || "".equals(loginId)) {
				System.out.println("### Margo ==> in if loginId : " + loginId);
				HashMap<Object, Object> map = new HashMap<Object, Object>();
				map.put("msg", "로그인이 필요합니다.");
				if(!methodName.equals("getXmlData")&&!methodName.equals("getStringData")) {
					return map;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("먼저 로그인을 하셔야 합니다.");
		}
		Object result = joinPoint.proceed();
		return result;
	}
			
}
