package com.example.pfad1.mappers;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.vos.cart.CartReadVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICartMapper {
    int deleteCart(CartEntity cartEntity);
    int selectCountCarts(CartEntity cartEntity);
    CartReadVo[] selectCarts(CartEntity cartEntity);
    int updateCart(CartEntity cartEntity);
}
