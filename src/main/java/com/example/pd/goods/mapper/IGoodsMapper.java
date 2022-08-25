package com.example.pd.goods.mapper;

import com.example.pd.cart.entity.CartEntity;
import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.vo.GoodsRegisterVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGoodsMapper {
    int deleteGoods(GoodsEntity goodsEntity);

    GoodsEntity[] selectGoodsArr(GoodsEntity goodsEntity);

    GoodsEntity selectGoods(GoodsEntity goodsEntity);

    int insertGoods(GoodsRegisterVo goodsRegisterVo);

    int updateGoods(GoodsEntity goodsEntity);

    int insertCart(CartEntity cartEntity);

    int selectCountCart(CartEntity cartEntity);

    int updateCart(CartEntity cartEntity);
}
