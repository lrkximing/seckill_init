package com.example.seckill_demo.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.seckill_demo.exception.GlobalException;
import com.example.seckill_demo.mapper.UserMapper;
import com.example.seckill_demo.pojo.User;
import com.example.seckill_demo.util.CookieUtil;
import com.example.seckill_demo.util.MD5Util;
import com.example.seckill_demo.util.UUIDUtil;
import com.example.seckill_demo.util.ValidatorUtil;
import com.example.seckill_demo.vo.LoginVo;
import com.example.seckill_demo.vo.RespBean;
import com.example.seckill_demo.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    //登录
    public RespBean dologin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile=loginVo.getMobile();
        String password=loginVo.getPassword();
        //查询用户
        User user=userMapper.getById(mobile);
        if(user==null){
            throw new GlobalException(RespBeanEnum.Mobile_not_exist);
        }
        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.Login_error);
        }
        //生成cookie（用于判断秒杀是用户是否登录）
        String ticket= UUIDUtil.uuid();
        //将用户信息存入redis中
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }

    //根据Cookie获取用户
    public User getuserbyCookie(String userTicket, HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:"+userTicket);
        if(user != null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
