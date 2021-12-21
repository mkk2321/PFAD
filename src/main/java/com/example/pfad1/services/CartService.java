package com.example.pfad1.services;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.cart.CartDeleteResult;
import com.example.pfad1.enums.cart.CartReadResult;
import com.example.pfad1.enums.cart.CartUpdateResult;
import com.example.pfad1.mappers.ICartMapper;
import com.example.pfad1.vos.cart.CartDeleteVo;
import com.example.pfad1.vos.cart.CartReadVo;
import com.example.pfad1.vos.cart.CartUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private static class RegExp {
        public static final String PRODUCT_INDEX = "^([0-9]{0,3})$";
    }
    private final ICartMapper cartMapper;

    @Autowired
    public CartService(ICartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public static boolean checkProductIndex(String s) {
        return s != null && s.matches(RegExp.PRODUCT_INDEX);
    }

    public void read(CartReadVo cartReadVo, UserEntity userEntity) {
        if(userEntity == null) {
            cartReadVo.setResult(CartReadResult.NOT_ALLOWED);
            return;
        }
        cartReadVo.setUserId(userEntity.getId());
        CartReadVo[] cartReadVos = this.cartMapper.selectCarts(cartReadVo);
        cartReadVo.setCartReadVos(cartReadVos);
        cartReadVo.setCartCount(this.cartMapper.selectCountCarts(cartReadVo));
        cartReadVo.setResult(CartReadResult.SUCCESS);
    }

    public void delete(CartDeleteVo cartDeleteVo, UserEntity userEntity) {
        if(!CartService.checkProductIndex(String.valueOf(cartDeleteVo.getProductIndex()))) {
            cartDeleteVo.setResult(CartDeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null) {
            cartDeleteVo.setResult(CartDeleteResult.NOT_ALLOWED);
            return;
        }
//        int deleteCart = this.cartMapper.deleteCart(cartDeleteVo);
        if(this.cartMapper.deleteCart(cartDeleteVo) == 0) {
            cartDeleteVo.setResult(CartDeleteResult.CART_NOT_DEFINED);
            return;
        } else {
            cartDeleteVo.setResult(CartDeleteResult.SUCCESS);
        }
    }

    public void update(CartUpdateVo cartUpdateVo, UserEntity userEntity) {
/*        if(!CartService.checkProductIndex(String.valueOf(cartUpdateVo.getProductIndex()))) {
            cartUpdateVo.setResult(CartUpdateResult.NORMALIZATION_FAILURE);
            return;
        }*/
        cartUpdateVo.setUserId(userEntity.getId());
        this.cartMapper.updateCart(cartUpdateVo);
        cartUpdateVo.setResult(CartUpdateResult.SUCCESS);
    }
}
