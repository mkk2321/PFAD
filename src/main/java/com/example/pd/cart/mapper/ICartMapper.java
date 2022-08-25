package com.example.pd.cart.mapper;

import com.example.pd.cart.entity.CartEntity;
import com.example.pd.cart.entity.OrderEntity;
import com.example.pd.cart.vo.CartReadVo;
import com.example.pd.interfaces.IUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICartMapper {
    int deleteCart(CartEntity cartEntity);

    int deleteCartAll(IUser iuser);

    int selectCountCarts(CartEntity cartEntity);

    CartReadVo[] selectCarts(IUser iUser);

    int updateCart(CartEntity cartEntity);

    int updateGoodsStock(OrderEntity orderEntity);

    int insertOrder(OrderEntity orderEntity);

    OrderEntity selectOrder(IUser iUser);

    OrderEntity[] selectOrders(IUser iUser);

    int deleteOrder(OrderEntity orderEntity);

}
