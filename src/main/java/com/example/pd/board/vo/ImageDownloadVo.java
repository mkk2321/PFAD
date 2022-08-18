package com.example.pd.board.vo;

import com.example.pd.board.entity.ImageEntity;
import com.example.pd.board.enums.ImageDownloadResult;
import com.example.pd.interfaces.IResult;

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
