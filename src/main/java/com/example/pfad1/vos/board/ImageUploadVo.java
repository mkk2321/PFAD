package com.example.pfad1.vos.board;

import com.example.pfad1.enums.board.ImageUploadResult;
import com.example.pfad1.interfaces.IResult;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadVo implements IResult<ImageUploadResult> {
    private MultipartFile file;
    private ImageUploadResult result;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public ImageUploadResult getResult() {
        return result;
    }

    @Override
    public void setResult(ImageUploadResult result) {
        this.result = result;
    }
}
