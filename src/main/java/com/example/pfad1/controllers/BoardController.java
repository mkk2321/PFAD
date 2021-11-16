package com.example.pfad1.controllers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.services.BoardService;
import com.example.pfad1.vos.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return "board/board";
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

    @RequestMapping(value = "/list/read/{articleIndex}")
    public String readGet(@PathVariable(name="articleIndex")int articleIndex,
                          HttpServletRequest request,
                          @SessionAttribute(name = "userEntity", required = false)UserEntity userEntity) {
        ReadVo readVo = new ReadVo();
        readVo.setIndex(articleIndex);
        this.boardService.read(readVo, userEntity);
        request.setAttribute("readVo", readVo);
        return "board/read";
    }

    @RequestMapping(value = "/delete/{articleIndex}",
                method = RequestMethod.GET,
                produces = MediaType.TEXT_HTML_VALUE)
    public String deleteGet(@PathVariable(name = "articleIndex")int articleIndex,
                            @SessionAttribute(name = "userEntity")UserEntity userEntity,
                            HttpServletRequest request) {
        DeleteVo deleteVo = new DeleteVo();
        deleteVo.setIndex(articleIndex);
        this.boardService.deleteArticle(userEntity, deleteVo);
        request.setAttribute("deleteVo", deleteVo);
        return "board/delete";
    }


    @RequestMapping(value = "/write/{boardCode}")
    public String writeGet(@PathVariable(name = "boardCode")String boardCode,
                           @SessionAttribute(name = "userEntity")UserEntity userEntity,
                           HttpServletRequest request) {
        WriteVo writeVo = new WriteVo();
        writeVo.setBoardCode(boardCode);
        this.boardService.writeByGet(writeVo,userEntity);
        request.setAttribute("writeVo", writeVo);
        return "board/write";
    }

    @ResponseBody
    @RequestMapping(value = "/upload-image",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadImagePost(@RequestParam(name = "upload") MultipartFile file,
                                  @SessionAttribute(name = "userEntity", required = false)UserEntity userEntity) {
        ImageUploadVo imageUploadVo = new ImageUploadVo();
        imageUploadVo.setFile(file);
        this.boardService.uploadImage(imageUploadVo, userEntity);
        return "";
    }
}
