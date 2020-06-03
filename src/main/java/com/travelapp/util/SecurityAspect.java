package com.travelapp.util;

import com.travelapp.exceptions.AuthenticationException;
import com.travelapp.exceptions.AuthorizationException;
import com.travelapp.web.dtos.Principal;
import com.travelapp.web.security.Secured;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class SecurityAspect {

    private HttpServletRequest request;

    @Autowired
    public SecurityAspect(HttpServletRequest req) {
        this.request = req;
    }

    @Around("@annotation(com.travelapp.web.security.Secured)")
    public Object secureEndpoint(ProceedingJoinPoint pjp) throws Throwable {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Secured ctrlrAnnotation = method.getAnnotation(Secured.class);

        List<String> allowedRoles = Arrays.asList(ctrlrAnnotation.allowedRoles());
        Principal principal = (Principal) request.getAttribute("principal");

        System.out.println("I am in SecurityAspect " + principal);
        System.out.println(principal.getRole());

        if (principal == null) {
            throw new AuthorizationException("An unauthenticated request was made to a protected endpoint.");
        }

        if (!allowedRoles.contains(principal.getRole())) {
            throw new AuthorizationException("A forbidden request was made by " + principal.getUsername() + principal+principal.getRole());
        }

        return pjp.proceed();

    }
}
