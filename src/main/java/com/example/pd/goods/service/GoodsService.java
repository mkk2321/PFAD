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

    private final IGoodsMapper goodsMapper;

    @Autowired
    public GoodsService(IGoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
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

    public void list(GoodsVo goodsVo){
        GoodsEntity[] goodsEntities = this.goodsMapper.selectGoodsArr(goodsVo);
        goodsVo.setGoodsEntities(goodsEntities);
        goodsVo.setResult(ListResult.SUCCESS);
    }

    /*public void registerByGet(GoodsRegisterVo goodsRegisterVo, UserEntity userEntity){
        if(userEntity == null || !userEntity.isAdmin()) {
            goodsRegisterVo.setResult(GoodsRegisterResult.NOT_ALLOWED);
            return;
        }
        goodsRegisterVo.setResult(GoodsRegisterResult.SUCCESS);
    }*/

    public void registerByPost(GoodsRegisterVo goodsRegisterVo, UserEntity userEntity){
        if(!GoodsService.checkName(goodsRegisterVo.getName()) ||
        !GoodsService.checkPrice(String.valueOf(goodsRegisterVo.getPrice())) ||
        !GoodsService.checkStock(String.valueOf(goodsRegisterVo.getStock())) ||
        !GoodsService.checkDescription(goodsRegisterVo.getDescription()) ||
        !GoodsService.checkFileName(goodsRegisterVo.getFileName())) {
            goodsRegisterVo.setResult(GoodsRegisterResult.NORMALIZATION_FAILURE);
            return;
        }

        if(userEntity == null || !userEntity.isAdmin()) {
            goodsRegisterVo.setResult(GoodsRegisterResult.NOT_ALLOWED);
            return;
        }
        this.goodsMapper.insertGoods(goodsRegisterVo);
        goodsRegisterVo.setResult(GoodsRegisterResult.SUCCESS);
    }

    public void read(GoodsReadVo goodsReadVo) {
        if(!GoodsService.checkIndex(String.valueOf(goodsReadVo.getIndex()))) {
            goodsReadVo.setResult(GoodsReadResult.NORMALIZATION_FAILURE);
            return;
        }
        GoodsEntity goodsEntity = this.goodsMapper.selectGoods(goodsReadVo);
        if(goodsEntity == null) {
            goodsReadVo.setResult(GoodsReadResult.GOODS_NOT_DEFINED);
            return;
        }
        goodsReadVo.setName(goodsEntity.getName());
        goodsReadVo.setPrice(goodsEntity.getPrice());
        goodsReadVo.setStock(goodsEntity.getStock());
        goodsReadVo.setDescription(goodsEntity.getDescription());
        goodsReadVo.setThumbnail(goodsEntity.getThumbnail());
        goodsReadVo.setResult(GoodsReadResult.SUCCESS);
    }

    public void addCart(CartAddVo cartAddVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(cartAddVo.getGoodsIndex())) ||
        !GoodsService.checkStock(String.valueOf(cartAddVo.getStock()))) {
            cartAddVo.setResult(CartAddResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null) {
            cartAddVo.setResult(CartAddResult.NOT_ALLOWED);
            return;
        }
        cartAddVo.setUserId(userEntity.getId());
        if(this.goodsMapper.selectCountCart(cartAddVo) == 1) {
            this.goodsMapper.updateCart(cartAddVo);
        }else {
            this.goodsMapper.insertCart(cartAddVo);
        }
        cartAddVo.setResult(CartAddResult.SUCCESS);
    }

    public void delete(GoodsDeleteVo goodsDeleteVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(goodsDeleteVo.getIndex()))) {
            goodsDeleteVo.setResult(GoodsDeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null || !userEntity.isAdmin()) {
            goodsDeleteVo.setResult(GoodsDeleteResult.NOT_ALLOWED);
            return;
        }
        this.goodsMapper.deleteGoods(goodsDeleteVo);
        goodsDeleteVo.setResult(GoodsDeleteResult.SUCCESS);
    }

    public void modifyByGet(GoodsModifyVo goodsModifyVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(goodsModifyVo.getIndex()))) {
            goodsModifyVo.setResult(GoodsModifyResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null || !userEntity.isAdmin()) {
            goodsModifyVo.setResult(GoodsModifyResult.NOT_ALLOWED);
            return;
        }
        GoodsEntity goodsEntity = this.goodsMapper.selectGoods(goodsModifyVo);
        if(goodsEntity == null) {
            goodsModifyVo.setResult(GoodsModifyResult.NOT_GOODS_DEFINED);
            return;
        }
        goodsModifyVo.setName(goodsEntity.getName());
        goodsModifyVo.setPrice(goodsEntity.getPrice());
        goodsModifyVo.setStock(goodsEntity.getStock());
        goodsModifyVo.setDescription(goodsEntity.getDescription());
        goodsModifyVo.setThumbnail(goodsEntity.getThumbnail());
        goodsModifyVo.setResult(GoodsModifyResult.SUCCESS);
    }

    public void modifyByPost(GoodsModifyVo goodsModifyVo, UserEntity userEntity) {
        if(!GoodsService.checkIndex(String.valueOf(goodsModifyVo.getIndex())) ||
        !GoodsService.checkName(goodsModifyVo.getName())||
        !GoodsService.checkPrice(String.valueOf(goodsModifyVo.getPrice()))||
        !GoodsService.checkStock(String.valueOf(goodsModifyVo.getStock()))||
        !GoodsService.checkDescription(goodsModifyVo.getDescription())){
            goodsModifyVo.setResult(GoodsModifyResult.NORMALIZATION_FAILURE);
            System.out.println(goodsModifyVo.getDescription());
            return;
        }
        if(userEntity == null || !userEntity.isAdmin()) {
            goodsModifyVo.setResult(GoodsModifyResult.NOT_ALLOWED);
            return;
        }
        this.goodsMapper.updateGoods(goodsModifyVo);
        goodsModifyVo.setResult(GoodsModifyResult.SUCCESS);
    }

}
