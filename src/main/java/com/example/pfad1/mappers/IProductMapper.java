package com.example.pfad1.mappers;

import com.example.pfad1.entities.ImageEntity;
import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.vos.ImageUploadVo;
import org.apache.ibatis.annotations.Mapper;

import java.awt.*;

@Mapper
public interface IProductMapper {
    ProductEntity[] selectProduct(ProductEntity productEntity);

    int insertImage(ImageUploadVo imageUploadVo);
    int selectLastInsertId();

    ImageEntity selectImage(ImageEntity imageEntity);
}
