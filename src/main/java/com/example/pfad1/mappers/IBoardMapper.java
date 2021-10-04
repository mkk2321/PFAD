package com.example.pfad1.mappers;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.vos.board.ListVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IBoardMapper {
    int selectBoardCount(BoardEntity boardEntity);

    BoardEntity selectBoard(BoardEntity boardEntity);

    ArticleEntity[] selectArticlesByList(ListVo listVo);

    int selectArticleCount(BoardEntity boardEntity);
}
