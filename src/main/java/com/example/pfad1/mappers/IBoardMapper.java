package com.example.pfad1.mappers;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.interfaces.IBoard;
import com.example.pfad1.vos.board.ListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IBoardMapper {
    ArticleEntity selectArticle(ArticleEntity articleEntity);

    ArticleEntity[] selectArticlesByList(ListVo listVo);

    BoardEntity selectBoard(IBoard board);

    int selectArticleCount(BoardEntity boardEntity);

    int selectArticleCountGreaterThan(ArticleEntity articleEntity);

    int updateArticleView(ArticleEntity articleEntity);
}
