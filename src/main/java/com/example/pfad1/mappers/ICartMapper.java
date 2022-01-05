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

    int deleteCartAll(CartEntity cartEntity);

    int selectCountCarts(CartEntity cartEntity);

    CartReadVo[] selectCarts(CartEntity cartEntity);

    int updateCart(CartEntity cartEntity);

    int insertOrder(OrderEntity orderEntity);

    OrderByCartVo selectOrder(OrderByCartVo orderByCartVo);

    ProductEntity[] selectCartByProduct(IUser iuser);

}
