package com.example.seckill_demo.util;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;


//MD5工具类
@Component
public class MD5Util {
    // MD5加密
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    //盐（加密用的）
    private static final String salt = "1a2b3c4d";
    //第一次MD5加密，用于网络传输
    public static String inputPassToFormPass(String inputPass){
        //避免在网络传输被截取然后反推出密码，所以在md5加密前先打乱密码
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    // 第二次MD5加密，用于存储到数据库
    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    //合并（实际使用）
    public static String inputPassToDbPass(String input, String saltDB){
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

}
