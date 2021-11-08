package com.example.seckill_demo.vo;

import com.example.seckill_demo.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


//商品列表页面对应的类(继承商品类)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
    private Integer stockCount;
    private BigDecimal seckillPrice;
    private Date startDate;
    private Date endDate;
    private int version;

}

