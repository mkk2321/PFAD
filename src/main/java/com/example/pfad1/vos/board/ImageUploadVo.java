package com.example.pfad1.vos.board;

import com.example.pfad1.enums.board.ImageUploadResult;
import com.example.pfad1.interfaces.IResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageUploadVo implements IResult<ImageUploadResult> {
    private MultipartFile file;
    private ImageUploadResult result;
    private int index;

    public MultipartFile getFile() {
        return file;
    }


    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public byte[] getFileBytes() throws IOException{
        return this.file.getBytes();
    }

    public String getFileMime() {
        return this.file.getContentType();
    }

    @Override
    public ImageUploadResult getResult() {
        return result;
    }

    @Override
    public void setResult(ImageUploadResult result) {
        this.result = result;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
