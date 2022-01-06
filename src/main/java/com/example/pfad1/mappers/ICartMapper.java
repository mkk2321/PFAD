package com.example.pfad1.mappers;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.entities.cart.OrderEntity;
import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.interfaces.IUser;
import com.example.pfad1.vos.cart.CartReadVo;
import com.example.pfad1.vos.cart.OrderByCartVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICartMapper {
    int deleteCart(CartEntity cartEntity);

    int deleteCartAll(IUser iuser);

    int selectCountCarts(CartEntity cartEntity);

    CartReadVo[] selectCarts(IUser iUser);

    int updateCart(CartEntity cartEntity);

    int updateProductStock(OrderEntity orderEntity);

    int insertOrder(OrderEntity orderEntity);

    OrderEntity selectOrder(IUser iUser);

    OrderEntity[] selectOrders(IUser iUser);

}
