package com.example.seckill_demo.controller;

import com.example.seckill_demo.pojo.User;
import com.example.seckill_demo.service.GoodsService;
import com.example.seckill_demo.service.UserService;
import com.example.seckill_demo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

//商品
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    //跳转到商品列表页
    @RequestMapping("/to_list")
    public String toList(Model model,User user){
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goods_list";
    }

    //跳转到商品详情页面
    @RequestMapping("/to_Detail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId){
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVobyGoodsId(goodsId);
        Date startdate = goodsVo.getStartDate();
        Date enddate = goodsVo.getEndDate();
        Date nowdate = new Date();
        //秒杀状态
        int seckillStatus = 0;
        //倒计时
        int remainSeconds = 0;
        if(nowdate.before(startdate)){
            remainSeconds = ((int)((startdate.getTime()-nowdate.getTime())/1000));
        }else if(nowdate.after(enddate)){
            seckillStatus = 2;
            remainSeconds = -1;
        }else {
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("goods",goodsVo);
        return "goods_detail";
    }
}
