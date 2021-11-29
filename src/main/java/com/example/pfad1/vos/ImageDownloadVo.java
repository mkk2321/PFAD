package com.example.pfad1.vos;

import com.example.pfad1.entities.ImageEntity;
import com.example.pfad1.enums.ImageDownloadResult;
import com.example.pfad1.interfaces.IResult;

public class ImageDownloadVo extends ImageEntity implements IResult<ImageDownloadResult> {
    private ImageDownloadResult result;

    @Override
    public ImageDownloadResult getResult() {
        return result;
    }

    @Override
    public void setResult(ImageDownloadResult result) {
        this.result = result;
    }
}
