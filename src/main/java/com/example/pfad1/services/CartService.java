package com.example.pfad1.services;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.cart.CartReadResult;
import com.example.pfad1.vos.cart.CartReadVo;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    public void read(CartReadVo cartReadVo, UserEntity userEntity) {
        if(userEntity == null) {
            cartReadVo.setResult(CartReadResult.NOT_ALLOWED);
            return;
        }
    }
}
