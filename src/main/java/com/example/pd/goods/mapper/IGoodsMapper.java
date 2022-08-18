package com.example.pd.goods.mapper;

import com.example.pd.cart.entity.CartEntity;
import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.vo.GoodsRegisterVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGoodsMapper {
    int deleteProduct(GoodsEntity productEntity);

    GoodsEntity[] selectProducts(GoodsEntity productEntity);

    GoodsEntity selectProduct(GoodsEntity productEntity);

    int insertProduct(GoodsRegisterVo productRegisterVo);

    int updateProduct(GoodsEntity productEntity);

    int insertCart(CartEntity cartEntity);

    int selectCountCart(CartEntity cartEntity);

    int updateCart(CartEntity cartEntity);
}
