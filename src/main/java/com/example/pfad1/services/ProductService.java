package com.example.pfad1.services;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.product.ProductRegisterResult;
import com.example.pfad1.mappers.IProductMapper;
import com.example.pfad1.vos.product.ProductRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static class RegExp {
        public static final String NAME = "^([a-zA-Z가-힣0-9 ]{0,})$";
        public static final String PRICE = "^([0-9]{0,})$";
        public static final String STOCK = "^([0-9]{0,5})$";
        public static final String DESCRIPTION = "^[a-zA-Z가-힣0-9 `~!@#$%^&*\\(\\)\\[\\]\\{\\}_-+=|'\";:/?.><,]{0,}$";
        public static final String FILE = "^[a-zA-Z가-힣0-9 `~!@#$%^&*\\(\\)\\[\\]\\{\\}_-+=|'\";:/?.><,]{0,}$";

        private RegExp(){

        }
    }

    private final IProductMapper productMapper;

    @Autowired
    public ProductService(IProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public static boolean checkName(String s) {
        return s != null && s.matches(RegExp.NAME);
    }

    public static boolean checkPrice(String s) {
        return s != null && s.matches(RegExp.PRICE);
    }

    public static boolean checkStock(String s) {
        return s != null && s.matches(RegExp.STOCK);
    }

    public static boolean checkDescription(String s) {
        return s != null && s.matches(RegExp.DESCRIPTION);
    }

    public static boolean checkFile(String s) {
        return s != null && s.matches(RegExp.FILE);
    }

    public void register(ProductRegisterVo productRegisterVo, UserEntity userEntity) {
        if(!ProductService.checkName(productRegisterVo.getName()) ||
        !ProductService.checkPrice(String.valueOf(productRegisterVo.getPrice())) ||
        !ProductService.checkStock(String.valueOf(productRegisterVo.getStock())) ||
        !ProductService.checkDescription(productRegisterVo.getDescription()) ||
        !ProductService.checkFile(productRegisterVo.getFile())) {
            productRegisterVo.setResult(ProductRegisterResult.NORMALIZATION_FAILURE);
            return;
        }

        if(!userEntity.isAdmin() || userEntity == null) {
            // TODO : Why userEntity == null is false?
        }


    }
}
