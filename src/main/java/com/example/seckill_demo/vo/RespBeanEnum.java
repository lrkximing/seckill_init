package com.example.seckill_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//公共返回对象枚举（异常信息）
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
      SUCCESS(200,"成功"),
      ERROR(500,"服务端异常"),
      Login_error(500210,"账号或密码不正确"),
      Mobile_error(500211,"手机号码格式不正确"),
      Mobile_not_exist(500212,"账号不存在"),
      Bind_error(500213,"参数校验异常"),
      Empty_Stock(500500,"库存不足"),
      Repeat_error(500501,"该商品每人限购一件");

      private final Integer code;
      private final String message;
}
