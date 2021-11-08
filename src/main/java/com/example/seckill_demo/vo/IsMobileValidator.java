package com.example.seckill_demo.vo;

import com.example.seckill_demo.util.ValidatorUtil;
import com.example.seckill_demo.validator.IsMobile;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
//实现自定义注解的类
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return ValidatorUtil.isMobile(s);
        }else {
            if(StringUtils.isEmpty(s)){
                return true;
            }else {
                return ValidatorUtil.isMobile(s);
            }
        }
    }
}
