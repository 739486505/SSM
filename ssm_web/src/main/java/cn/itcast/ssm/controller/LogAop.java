package cn.itcast.ssm.controller;


import cn.itcast.ssm.SysLog;
import cn.itcast.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法

    @Before("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取访问时间
        startTime = new Date();
        //获取访问类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法
        String methodName = jp.getSignature().getName();//获取访问方法名称
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod(methodName);
        } else {
            Class[] classeArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classeArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classeArgs);
        }

    }

    @After("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        //获取访问时长
        Long executionTime = System.currentTimeMillis() - startTime.getTime();

        //获取URL
        String url = "";
        if (executionClass != null && executionClass != LogAop.class && executionMethod != null) {
            //获取类上的RequestMapping
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            String[] classValue = classAnnotation.value();
            if (classAnnotation != null) {
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                String[] methodValue = methodAnnotation.value();
                url = classValue[0] + methodValue[0];
            }
        }

        //获取ip
        String ip = request.getRemoteAddr();

        //获取操作者
        SecurityContext context = SecurityContextHolder.getContext();
        //request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
        String username = ((User)
                (context.getAuthentication().getPrincipal())).getUsername();


        //将相关信息封装到Syslog对象
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(startTime);
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);

        //调用service存入数据库
        sysLogService.save(sysLog);

    }


}
