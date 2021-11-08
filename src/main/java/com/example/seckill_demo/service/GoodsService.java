package com.example.seckill_demo.service;

import com.example.seckill_demo.mapper.GoodsMapper;
import com.example.seckill_demo.pojo.SeckillGoods;
import com.example.seckill_demo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    //获取商品列表
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.listGoodsVo();
    }
    //获取商品详情
    public GoodsVo findGoodsVobyGoodsId(Long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }


}
