package com.example.pd.goods.service;

import com.example.pd.cart.enums.CartAddResult;
import com.example.pd.cart.vo.CartAddVo;
import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.ListResult;
import com.example.pd.goods.enums.GoodsDeleteResult;
import com.example.pd.goods.enums.GoodsModifyResult;
import com.example.pd.goods.enums.GoodsReadResult;
import com.example.pd.goods.enums.GoodsRegisterResult;
import com.example.pd.goods.mapper.IGoodsMapper;
import com.example.pd.goods.vo.GoodsDeleteVo;
import com.example.pd.goods.vo.GoodsModifyVo;
import com.example.pd.goods.vo.GoodsReadVo;
import com.example.pd.goods.vo.GoodsRegisterVo;
import com.example.pd.goods.vo.GoodsVo;
import com.example.pd.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    private static class RegExp {
        public static final String NAME = "^([a-zA-Z가-힣0-9 ]{0,})$";
        public static final String PRICE = "^([0-9]{0,})$";
        public static final String STOCK = "^([0-9]{0,})$";
        public static final String DESCRIPTION = "^[a-zA-Z가-힣0-9 \\r\\n`~!@#$%^&*\\(\\)\\[\\]\\{\\}_\\-+=|'\";:/?.><,]{0,}$";
        public static final String FILE_NAME = "^[a-zA-Z가-힣0-9 `~!@#$%^&*\\(\\)\\[\\]\\{\\}_\\-+=|'\";:/?.><,]{0,}$";
        public static final String INDEX = "^([0-9]{0,3})$";
        private RegExp(){

        }
    }

    private final IGoodsMapper productMapper;

    @Autowired
    public GoodsService(IGoodsMapper productMapper) {
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

    public static boolean checkFileName(String s) {
        return s != null && s.matches(RegExp.FILE_NAME);
    }

    public static boolean checkIndex(String s) { return s != null && s.matches(RegExp.INDEX); }

    public void list(GoodsVo productVo){
        GoodsEntity[] productEntities = this.productMapper.selectProducts(productVo);
        productVo.setProductEntities(productEntities);
        productVo.setResult(ListResult.SUCCESS);
    }

    /*public void registerByGet(ProductRegisterVo productRegisterVo, UserEntity userEntity){
        if(userEntity == null || !userEntity.isAdmin()) {
            productRegisterVo.setResult(ProductRegisterResult.NOT_ALLOWED);
            return;
        }
        productRegisterVo.setResult(ProductRegisterResult.SUCCESS);
    }*/

    public void registerByPost(GoodsRegisterVo productRegisterVo, UserEntity userEntity){
        if(!GoodsService.checkName(productRegisterVo.getName()) ||
        !GoodsService.checkPrice(String.valueOf(productRegisterVo.getPrice())) ||
        !GoodsService.checkStock(String.valueOf(productRegisterVo.getStock())) ||
        !GoodsService.checkDescription(productRegisterVo.getDescription()) ||
        !GoodsService.checkFileName(productRegisterVo.getFileName())) {
            productRegisterVo.setResult(GoodsRegisterResult.NORMALIZATION_FAILURE);
            return;
        }

        if(userEntity == null || !userEntity.isAdmin()) {
            productRegisterVo.setResult(GoodsRegisterResult.NOT_ALLOWED);
            return;
        }
        this.productMapper.insertProduct(productRegisterVo);
        productRegisterVo.setResult(GoodsRegisterResult.SUCCESS);
    }

    public void read(GoodsReadVo productReadVo) {
        if(!GoodsService.checkIndex(String.valueOf(productReadVo.getIndex()))) {
            productReadVo.setResult(GoodsReadResult.NORMALIZATION_FAILURE);
            return;
        }
        GoodsEntity productEntity = this.productMapper.selectProduct(productReadVo);
        if(productEntity == null) {
            productReadVo.setResult(GoodsReadResult.PRODUCT_NOT_DEFINED);
            return;
        }
        productReadVo.setName(productEntity.getName());
        productReadVo.setPrice(productEntity.getPrice());
        productReadVo.setStock(productEntity.getStock());
        productReadVo.setDescription(productEntity.getDescription());
        productReadVo.setThumbnail(productEntity.getThumbnail());
        productReadVo.setResult(GoodsReadResult.SUCCESS);
    }

    public void addCart(CartAddVo cartAddVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(cartAddVo.getProductIndex())) ||
        !GoodsService.checkStock(String.valueOf(cartAddVo.getStock()))) {
            cartAddVo.setResult(CartAddResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null) {
            cartAddVo.setResult(CartAddResult.NOT_ALLOWED);
            return;
        }
        cartAddVo.setUserId(userEntity.getId());
        if(this.productMapper.selectCountCart(cartAddVo) == 1) {
            this.productMapper.updateCart(cartAddVo);
        }else {
            this.productMapper.insertCart(cartAddVo);
        }
        cartAddVo.setResult(CartAddResult.SUCCESS);
    }

    public void delete(GoodsDeleteVo productDeleteVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(productDeleteVo.getIndex()))) {
            productDeleteVo.setResult(GoodsDeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null || !userEntity.isAdmin()) {
            productDeleteVo.setResult(GoodsDeleteResult.NOT_ALLOWED);
            return;
        }
        this.productMapper.deleteProduct(productDeleteVo);
        productDeleteVo.setResult(GoodsDeleteResult.SUCCESS);
    }

    public void modifyByGet(GoodsModifyVo productModifyVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(productModifyVo.getIndex()))) {
            productModifyVo.setResult(GoodsModifyResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null || !userEntity.isAdmin()) {
            productModifyVo.setResult(GoodsModifyResult.NOT_ALLOWED);
            return;
        }
        GoodsEntity productEntity = this.productMapper.selectProduct(productModifyVo);
        if(productEntity == null) {
            productModifyVo.setResult(GoodsModifyResult.NOT_PRODUCT_DEFINED);
            return;
        }
        productModifyVo.setName(productEntity.getName());
        productModifyVo.setPrice(productEntity.getPrice());
        productModifyVo.setStock(productEntity.getStock());
        productModifyVo.setDescription(productEntity.getDescription());
        productModifyVo.setThumbnail(productEntity.getThumbnail());
        productModifyVo.setResult(GoodsModifyResult.SUCCESS);
    }

    public void modifyByPost(GoodsModifyVo productModifyVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(productModifyVo.getIndex())) ||
        !GoodsService.checkName(productModifyVo.getName())||
        !GoodsService.checkPrice(String.valueOf(productModifyVo.getPrice()))||
        !GoodsService.checkStock(String.valueOf(productModifyVo.getStock()))||
        !GoodsService.checkDescription(productModifyVo.getDescription())){
            productModifyVo.setResult(GoodsModifyResult.NORMALIZATION_FAILURE);
            System.out.println(productModifyVo.getDescription());
            return;
        }
        if(userEntity == null || !userEntity.isAdmin()) {
            productModifyVo.setResult(GoodsModifyResult.NOT_ALLOWED);
            return;
        }
        this.productMapper.updateProduct(productModifyVo);
        productModifyVo.setResult(GoodsModifyResult.SUCCESS);
    }

}
