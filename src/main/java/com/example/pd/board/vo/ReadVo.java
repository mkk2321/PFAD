package com.example.pd.board.vo;

import com.example.pd.board.entity.ArticleEntity;
import com.example.pd.board.entity.CommentEntity;
import com.example.pd.board.enums.ReadResult;
import com.example.pd.interfaces.IResult;

public class ReadVo extends ArticleEntity implements IResult<ReadResult> {
    private ReadResult result;
    private int boardPage;
    private CommentEntity[] comments;

    public int getBoardPage() {
        return boardPage;
    }

    public void setBoardPage(int boardPage) {
        this.boardPage = boardPage;
    }

    @Override
    public ReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(ReadResult result) {
        this.result = result;
    }

    public CommentEntity[] getComments() {
        return comments;
    }

    public void setComments(CommentEntity[] comments) {
        this.comments = comments;
    }
}
