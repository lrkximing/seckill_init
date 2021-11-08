package com.example.seckill_demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.seckill_demo.pojo.Order;
import com.example.seckill_demo.pojo.SeckillOrder;
import com.example.seckill_demo.pojo.User;
import com.example.seckill_demo.service.GoodsService;
import com.example.seckill_demo.service.OrderService;
import com.example.seckill_demo.service.SeckillGoodsService;
import com.example.seckill_demo.vo.GoodsVo;
import com.example.seckill_demo.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/doseckill")
    public String doseckill(Model model, User user, Long goodsId){
        if(user == null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goods=goodsService.findGoodsVobyGoodsId(goodsId);
        //判断库存
        System.out.println(goods.getStockCount());
        if(goods.getStockCount() < 1){
            model.addAttribute("errmsg", RespBeanEnum.Empty_Stock.getMessage());
            return "seckill_fail";
        }
        //判断是否重复抢购
        //SeckillOrder seckillOrder = seckillGoodsService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id",user.getId()).eq("goods_id",goodsId));
        SeckillOrder seckillOrder = seckillGoodsService.getOne(user.getId(),goodsId);
        System.out.println(seckillOrder == null);
        if(seckillOrder != null){
            model.addAttribute("errmsg", RespBeanEnum.Repeat_error.getMessage());
            return "seckill_fail";
        }
        Order order = orderService.seckill(user,goods);
        model.addAttribute("order",order);
        model.addAttribute("goods",goods);
        return "orderDetail";
    }
}
