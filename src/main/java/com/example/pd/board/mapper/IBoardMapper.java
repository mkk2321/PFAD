package com.example.pd.board.mapper;

import com.example.pd.board.entity.ArticleEntity;
import com.example.pd.board.entity.BoardEntity;
import com.example.pd.board.entity.CommentEntity;
import com.example.pd.board.entity.ImageEntity;
import com.example.pd.board.vo.ImageUploadVo;
import com.example.pd.board.vo.ListVo;
import com.example.pd.interfaces.IArticle;
import com.example.pd.interfaces.IBoard;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IBoardMapper {
    int insertArticle(ArticleEntity articleEntity);

    int insertComment(CommentEntity commentEntity);

    int insertImage(ImageUploadVo imageUploadVo);

    int deleteArticle(ArticleEntity articleEntity);

    int deleteComment(CommentEntity commentEntity);

    ArticleEntity selectArticle(IArticle iArticle);

    ArticleEntity[] selectArticlesByList(ListVo listVo);

    ArticleEntity[] selectArticlesForSearchContent(ListVo listVo);

    ArticleEntity[] selectArticlesForSearchTitle(ListVo listVo);

    ArticleEntity[] selectArticlesForSearchWriter(ListVo listVo);

    int selectArticleCount(BoardEntity boardEntity);

    int selectArticleCountForSearchByContent(ListVo listVo);

    int selectArticleCountForSearchByTitle(ListVo listVo);

    int selectArticleCountForSearchByWriter(ListVo listVo);

    int selectArticleCountGreaterThan(ArticleEntity articleEntity);

    BoardEntity selectBoard(IBoard board);

    CommentEntity[] selectComments(IArticle iArticle);

    ImageEntity selectImage(ImageEntity imageEntity);

    int selectLastInsertId();

//    Article 지우지 않고 delete_flag = true로 변경
    int updateArticleDeleted(ArticleEntity articleEntity);

    int updateArticleView(ArticleEntity articleEntity);

    int updateArticle(ArticleEntity articleEntity);


}
