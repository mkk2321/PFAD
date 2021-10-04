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

    @RequestMapping(value = "/list",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String listGet() {
        return "board/list";
    }

    @RequestMapping(value = {
            "/list/{boardCode}",
            "/list/{boardCode}/{boardPage}"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String listGet(@PathVariable(name = "boardCode") String boardCode,
                          @PathVariable(name = "boardPage") Optional<Integer> boardPage,
                          HttpServletRequest request) {
        ListVo listVo = new ListVo();
        listVo.setCode(boardCode);
        listVo.setPage(boardPage.orElse(1));
        this.boardService.list(listVo);
        request.setAttribute("listVo", listVo);
        return "board/something";
    }
}
