package com.example.seckill_demo.exception;

import com.example.seckill_demo.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//全局异常
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{
    private RespBeanEnum respBeanEnum;
}
