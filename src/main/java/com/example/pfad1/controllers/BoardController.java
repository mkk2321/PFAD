package com.example.pfad1.controllers;

import com.example.pfad1.services.BoardService;
import com.example.pfad1.vos.board.ListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String boardGet() {
        return "board/list";
    }



    /*
    *   /board/list/notice
    *   /board/list/free
    *   /board/list/qna
    */

    @RequestMapping(value = {
            "/list/{boardCode}",
            "/list/{boardCode}/{boardPage}"},
            method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String listGet(@PathVariable(name = "boardCode")String boardCode,
                          @PathVariable(name = "boardPage") Optional<Integer> boardPage,
                          HttpServletRequest request) {
//        PathVariable 사용하면 ListVo를 객체화 할 수 없다. 그래서 수동으로 객체화 해야한다.
        ListVo listVo = new ListVo();
        listVo.setCode(boardCode);
        listVo.setPage(boardPage.orElse(1));
//                     orElse : Optional이 값을 가지고 있으면 그 값을 반환하고 없으면 (1)을 반환

        this.boardService.list(listVo);
        request.setAttribute("listVo", listVo);
        return "board/board";
    }


}
