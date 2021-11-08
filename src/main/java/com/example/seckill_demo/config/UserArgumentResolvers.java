package com.example.seckill_demo.config;

import com.example.seckill_demo.pojo.User;
import com.example.seckill_demo.service.UserService;
import com.example.seckill_demo.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//自定义用户参数
@Component
public class UserArgumentResolvers implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String ticket = CookieUtil.getCookieValue(request,"userTicket");
        if(StringUtils.isEmpty(ticket)){
            return null;
        }
        return userService.getuserbyCookie(ticket,request,response);
    }
}
