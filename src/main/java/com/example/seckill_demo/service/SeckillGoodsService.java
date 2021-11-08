package com.example.seckill_demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.seckill_demo.mapper.OrderMapper;
import com.example.seckill_demo.pojo.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillGoodsService {
    @Autowired
    private OrderMapper orderMapper;
    public SeckillOrder getOne(long userId,long goodsId) {
        return orderMapper.getOrderByUserIdGoodsId(userId,goodsId);
    }
}
