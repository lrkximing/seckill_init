package com.example.seckill_demo.service;

import com.example.seckill_demo.mapper.GoodsMapper;
import com.example.seckill_demo.mapper.OrderMapper;
import com.example.seckill_demo.pojo.Order;
import com.example.seckill_demo.pojo.SeckillGoods;
import com.example.seckill_demo.pojo.SeckillOrder;
import com.example.seckill_demo.pojo.User;
import com.example.seckill_demo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    public Order seckill(User user, GoodsVo goods) {
        SeckillGoods sg = new SeckillGoods();
        sg.setGoodsId(goods.getId());
        goodsMapper.reduceStock(sg);
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(8L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        seckillOrder.setGoodsId(goods.getId());
        orderMapper.insertSeckillOrder(seckillOrder);
        return order;
    }
}
