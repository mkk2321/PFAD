package com.example.pfad1.services;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.product.ListResult;
import com.example.pfad1.enums.product.ProductDeleteResult;
import com.example.pfad1.enums.product.ProductReadResult;
import com.example.pfad1.enums.product.ProductRegisterResult;
import com.example.pfad1.mappers.IProductMapper;
import com.example.pfad1.vos.product.ProductDeleteVo;
import com.example.pfad1.vos.product.ProductReadVo;
import com.example.pfad1.vos.product.ProductRegisterVo;
import com.example.pfad1.vos.product.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static class RegExp {
        public static final String NAME = "^([a-zA-Z가-힣0-9]{0,})$";
        public static final String PRICE = "^([0-9]{0,})$";
        public static final String STOCK = "^([0-9]{0,5})$";
        public static final String DESCRIPTION = "^[a-zA-Z가-힣0-9 `~!@#$%^&*\\(\\)\\[\\]\\{\\}_\\-+=|'\";:/?.><,]{0,}$";
        public static final String FILE_NAME = "^[a-zA-Z가-힣0-9 `~!@#$%^&*\\(\\)\\[\\]\\{\\}_\\-+=|'\";:/?.><,]{0,}$";
        public static final String INDEX = "^([0-9]{0,3})$";
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

    public static boolean checkFileName(String s) {
        return s != null && s.matches(RegExp.FILE_NAME);
    }

    public static boolean checkIndex(String s) { return s != null && s.matches(RegExp.INDEX); }

    public void list(ProductVo productVo){
        ProductEntity[] productEntities = this.productMapper.selectProducts(productVo);
        productVo.setProductEntities(productEntities);
        productVo.setResult(ListResult.SUCCESS);
    }

    public void registerByGet(ProductRegisterVo productRegisterVo, UserEntity userEntity){
        if(userEntity == null || !userEntity.isAdmin()) {
            productRegisterVo.setResult(ProductRegisterResult.NOT_ALLOWED);
            return;
        }
        productRegisterVo.setResult(ProductRegisterResult.SUCCESS);
    }

    public void registerByPost(ProductRegisterVo productRegisterVo, UserEntity userEntity){
        if(!ProductService.checkName(productRegisterVo.getName()) ||
        !ProductService.checkPrice(String.valueOf(productRegisterVo.getPrice())) ||
        !ProductService.checkStock(String.valueOf(productRegisterVo.getStock())) ||
        !ProductService.checkDescription(productRegisterVo.getDescription()) ||
        !ProductService.checkFileName(productRegisterVo.getFileName())) {
            productRegisterVo.setResult(ProductRegisterResult.NORMALIZATION_FAILURE);
            return;
        }

        if(userEntity == null || userEntity.isAdmin()) {
            productRegisterVo.setResult(ProductRegisterResult.NOT_ALLOWED);
            return;
        }
        this.productMapper.insertProduct(productRegisterVo);
        productRegisterVo.setResult(ProductRegisterResult.SUCCESS);
    }

    public void read(ProductReadVo productReadVo) {
        if(!ProductService.checkIndex(String.valueOf(productReadVo.getIndex()))) {
            productReadVo.setResult(ProductReadResult.NORMALIZATION_FAILURE);
            return;
        }
        ProductEntity productEntity = this.productMapper.selectProduct(productReadVo);
        if(productEntity == null) {
            productReadVo.setResult(ProductReadResult.PRODUCT_NOT_DEFINED);
            return;
        }
        productReadVo.setName(productEntity.getName());
        productReadVo.setPrice(productEntity.getPrice());
        productReadVo.setStock(productEntity.getStock());
        productReadVo.setDescription(productEntity.getDescription());
        productReadVo.setThumbnail(productEntity.getThumbnail());
        productReadVo.setResult(ProductReadResult.SUCCESS);
    }

    public void delete(ProductDeleteVo productDeleteVo, UserEntity userEntity) {
        if(!ProductService.checkIndex(String.valueOf(productDeleteVo.getIndex()))) {
            productDeleteVo.setResult(ProductDeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        if(userEntity == null || !userEntity.isAdmin()) {
            productDeleteVo.setResult(ProductDeleteResult.NOT_ALLOWED);
            return;
        }
        this.productMapper.deleteProduct(productDeleteVo);
        productDeleteVo.setResult(ProductDeleteResult.SUCCESS);
    }

}
