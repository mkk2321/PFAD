package com.example.pfad1.services;

import com.example.pfad1.entities.ImageEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.ImageDownloadResult;
import com.example.pfad1.enums.ImageUploadResult;
import com.example.pfad1.enums.product.ListResult;
import com.example.pfad1.mappers.IProductMapper;
import com.example.pfad1.vos.ImageDownloadVo;
import com.example.pfad1.vos.ImageUploadVo;
import com.example.pfad1.vos.product.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    private static class Config {
        public static final String[] ALLOWED_IMAGE_MIMES = new String[]{"image/jpeg", "image/png"};
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

    public void list(ProductVo productVo){
        this.productMapper.selectProduct(productVo);
        productVo.setResult(ListResult.SUCCESS);
    }

    public void registerByGet(){

    }

    public void uploadImage(ImageUploadVo imageUploadVo, UserEntity userEntity) {
        if (userEntity == null) {
            imageUploadVo.setResult(ImageUploadResult.NOT_ALLOWED);
            return;
        }

        if (imageUploadVo.getFile() == null ||
                imageUploadVo.getFile().getContentType() == null ||
                Arrays.stream(ProductService.Config.ALLOWED_IMAGE_MIMES).noneMatch(imageUploadVo.getFile().getContentType()::equals)) {
            imageUploadVo.setResult(ImageUploadResult.MIME_INVALID);
            return;
        }
        this.productMapper.insertImage(imageUploadVo);
        imageUploadVo.setIndex(this.productMapper.selectLastInsertId());
        imageUploadVo.setResult(ImageUploadResult.SUCCESS);
    }
    public void downloadImage(ImageDownloadVo imageDownloadVo) {
        ImageEntity imageEntity = this.productMapper.selectImage(imageDownloadVo);
        if (imageEntity == null) {
            imageDownloadVo.setResult(ImageDownloadResult.NOT_DEFINED);
            return;
        }
        imageDownloadVo.setCreatedAt(imageEntity.getCreatedAt());
        imageDownloadVo.setFile(imageEntity.getFile());
        imageDownloadVo.setMime(imageEntity.getMime());
        imageDownloadVo.setResult(ImageDownloadResult.SUCCESS);
    }




}
