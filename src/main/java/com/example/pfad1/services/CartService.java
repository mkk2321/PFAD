package com.example.pfad1.services;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.cart.*;
import com.example.pfad1.mappers.ICartMapper;
import com.example.pfad1.vos.cart.CartDeleteVo;
import com.example.pfad1.vos.cart.CartReadVo;
import com.example.pfad1.vos.cart.CartUpdateVo;
import com.example.pfad1.vos.cart.OrderByCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CartService {
    private static class RegExp {
        public static final String PRODUCT_INDEX = "^([0-9]{0,3})$";
        public static final String STOCK = "^([0-9]{0,3})$";
    }

    private final ICartMapper cartMapper;

    @Autowired
    public CartService(ICartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public static boolean checkProductIndex(String s) {
        return s != null && s.matches(RegExp.PRODUCT_INDEX);
    }

    public static boolean checkStock(String s) {
        return s != null && s.matches(RegExp.STOCK);
    }

    public void read(CartReadVo cartReadVo, UserEntity userEntity) {
        if (userEntity == null) {
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
        if (!CartService.checkProductIndex(String.valueOf(cartDeleteVo.getProductIndex()))) {
            cartDeleteVo.setResult(CartDeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        if (userEntity == null) {
            cartDeleteVo.setResult(CartDeleteResult.NOT_ALLOWED);
            return;
        }
        cartDeleteVo.setUserId(userEntity.getId());
//        int deleteCart = this.cartMapper.deleteCart(cartDeleteVo);
        if (this.cartMapper.deleteCart(cartDeleteVo) == 0) {
            cartDeleteVo.setResult(CartDeleteResult.CART_NOT_DEFINED);
            return;
        } else {
            cartDeleteVo.setResult(CartDeleteResult.SUCCESS);
        }
    }

    public void deleteAll(CartDeleteVo cartDeleteVo, UserEntity userEntity) {
        if (userEntity == null) {
            cartDeleteVo.setResult(CartDeleteResult.NOT_ALLOWED);
            return;
        }
        cartDeleteVo.setUserId(userEntity.getId());
        this.cartMapper.deleteCartAll(cartDeleteVo);
        cartDeleteVo.setResult(CartDeleteResult.SUCCESS);
    }

    public void update(CartUpdateVo cartUpdateVo, UserEntity userEntity) {
        for (int i = 0; i < cartUpdateVo.getProductsIndex().length; i++) {
            if (!CartService.checkProductIndex(String.valueOf(cartUpdateVo.getProductsIndex()[i])) ||
                    !CartService.checkStock(String.valueOf(cartUpdateVo.getStocks()[i]))) {
                cartUpdateVo.setResult(CartUpdateResult.NORMALIZATION_FAILURE);
                return;
            }
        }
        if (userEntity == null) {
            cartUpdateVo.setResult(CartUpdateResult.NOT_ALLOWED);
            return;
        }
        cartUpdateVo.setUserId(userEntity.getId());
        for (int i = 0; i < cartUpdateVo.getProductsIndex().length; i++) {
            cartUpdateVo.setProductIndex(cartUpdateVo.getProductsIndex()[i]);
            cartUpdateVo.setStock(cartUpdateVo.getStocks()[i]);
            this.cartMapper.updateCart(cartUpdateVo);
        }
        cartUpdateVo.setResult(CartUpdateResult.SUCCESS);
    }

    public void order(CartReadVo cartReadVo, UserEntity userEntity) {
        if (userEntity == null) {
            cartReadVo.setResult(CartReadResult.NOT_ALLOWED);
            return;
        }



        // db에 주문 정보 저장
        // order 테이블을 기반으로 user, product, cart 정보 불러오기
        // 장바구니 db 지우기
        // product 테이블에 접근하여 주문한 수량만큼 product에서 stock 빼기
        // 불러온 db를 model로 view에 뿌리기
    }

    public void orderComplete(OrderByCartVo orderByCartVo, UserEntity userEntity) {
        if (userEntity == null) {
            orderByCartVo.setResult(OrderByCartResult.NOT_ALLOWED);
            return;
        }
        LocalDate now = LocalDate.now();
        String orderCode = Integer.toString(now.getYear()) + (int)(Math.random() * (9999 - 1000 + 1)) + 1000;
//        orderByCartVo.setOrderCode(orderCode);
    }
}
