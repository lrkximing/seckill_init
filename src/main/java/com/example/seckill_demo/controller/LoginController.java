package com.example.seckill_demo.controller;


import com.example.seckill_demo.service.UserService;
import com.example.seckill_demo.vo.LoginVo;
import com.example.seckill_demo.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    //跳转登陆页面
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    //登录
    @RequestMapping("/do_login")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        return userService.dologin(loginVo,request,response);
    }
}
