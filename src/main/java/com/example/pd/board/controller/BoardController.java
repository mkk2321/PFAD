package com.example.pd.board.controller;

import com.example.pd.board.enums.CommentWriteResult;
import com.example.pd.board.enums.ImageDownloadResult;
import com.example.pd.board.enums.ImageUploadResult;
import com.example.pd.board.enums.WriteResult;
import com.example.pd.board.service.BoardService;
import com.example.pd.board.vo.CommentDeleteVo;
import com.example.pd.board.vo.CommentWriteVo;
import com.example.pd.board.vo.DeleteVo;
import com.example.pd.board.vo.ImageDownloadVo;
import com.example.pd.board.vo.ImageUploadVo;
import com.example.pd.board.vo.ListVo;
import com.example.pd.board.vo.ModifyVo;
import com.example.pd.board.vo.ReadVo;
import com.example.pd.board.vo.WriteVo;
import com.example.pd.handler.models.ClientModel;
import com.example.pd.user.entity.UserEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequestMapping(value = {
            "/list/{boardCode}",
            "/list/{boardCode}/{boardPage}"},
            method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String listGet(@PathVariable(name = "boardCode") String boardCode,
                          @PathVariable(name = "boardPage") Optional<Integer> boardPage,
                          HttpServletRequest request,
                          ListVo listVo) {
//        PathVariable 사용하면 ListVo를 객체화 할 수 없다. 그래서 수동으로 객체화 해야한다.
        listVo.setCode(boardCode);
        listVo.setPage(boardPage.orElse(1));
//                     orElse : Optional이 값을 가지고 있으면 그 값을 반환하고 없으면 (1)을 반환

        this.boardService.list(listVo);
        request.setAttribute("listVo", listVo);
        return "board/board";
    }

    @RequestMapping(value = "/{boardCode}/read/{articleIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String readGet(@PathVariable(name = "articleIndex") int articleIndex,
                          @PathVariable(name = "boardCode") String boardCode,
                          HttpServletRequest request,
                          @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity) {
        ReadVo readVo = new ReadVo();
        readVo.setIndex(articleIndex);
        this.boardService.read(readVo, userEntity);
        request.setAttribute("readVo", readVo);
        return "board/read";
    }

    @RequestMapping(value = "/{boardCode}/read/{articleIndex}",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String readPost(@PathVariable(name = "boardCode") String boardCode,
                           @PathVariable(name = "articleIndex") int articleIndex,
                           HttpServletRequest request,
                           CommentWriteVo commentWriteVo,
                           @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity) {
        commentWriteVo.setBoardCode(boardCode);
        commentWriteVo.setArticleIndex(articleIndex);
        this.boardService.putComment(userEntity, commentWriteVo);
        if (commentWriteVo.getResult() == CommentWriteResult.SUCCESS) {
            return "redirect:/board/" + commentWriteVo.getBoardCode() + "/read/" + articleIndex;
        } else {
            request.setAttribute("commentWriteResult", commentWriteVo.getResult());
            return "board/read";
        }
    }

    @RequestMapping(value = "/delete/{articleIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String deleteGet(@PathVariable(name = "articleIndex") int articleIndex,
                            @SessionAttribute(name = "userEntity") UserEntity userEntity,
                            HttpServletRequest request) {
        DeleteVo deleteVo = new DeleteVo();
        deleteVo.setIndex(articleIndex);
        this.boardService.deleteArticle(userEntity, deleteVo);
        request.setAttribute("deleteVo", deleteVo);
        return "board/delete";
    }


    @RequestMapping(value = "/write/{boardCode}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String writeGet(@PathVariable(name = "boardCode") String boardCode,
                           @SessionAttribute(name = "userEntity") UserEntity userEntity,
                           HttpServletRequest request) {
        WriteVo writeVo = new WriteVo();
        writeVo.setBoardCode(boardCode);
        this.boardService.writeByGet(writeVo, userEntity);
        request.setAttribute("writeVo", writeVo);
        return "board/write";
    }

    @RequestMapping(value = "/write/{boardCode}",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String writePost(@PathVariable(name = "boardCode") String boardCode,
                            @SessionAttribute(name = "userEntity") UserEntity userEntity,
                            WriteVo writeVo, // title과 content를 writeVo가 바로 받을 수 있게
                            HttpServletRequest request) {
        writeVo.setBoardCode(boardCode);
        this.boardService.writeByPost(writeVo, userEntity);
        if (writeVo.getResult() == WriteResult.SUCCESS) {
            return "redirect:/board/"+writeVo.getBoardCode()+"/read/" + writeVo.getIndex();
        } else {
            request.setAttribute("writeVo", writeVo);
            return "board/write";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/upload-image",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadImagePost(
            @RequestAttribute(value = "clientModel") ClientModel clientModel,
            @RequestParam(name = "upload") MultipartFile file,
                                  @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity) {
        ImageUploadVo imageUploadVo = new ImageUploadVo();
        imageUploadVo.setFile(file);
        this.boardService.uploadImage(imageUploadVo, userEntity);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", imageUploadVo.getResult().name().toLowerCase());
        if (imageUploadVo.getResult() == ImageUploadResult.SUCCESS) {
            responseJson.put("url", String.format(
                    "%s/board/download-image/%d", clientModel.getUrlPrefix(), imageUploadVo.getIndex()
            ));
        } else {
            String message;
            switch (imageUploadVo.getResult()) {
                case MIME_INVALID:
                    message = "허용하지 않은 이미지 형식입니다.";
                    break;
                case NOT_ALLOWED:
                    message = "이미지를 업로드 할 권한이 없습니다.";
                    break;
                default:
                    message = "알 수 없는 이유로 이미지를 업로드하지 못하였습니다.";
            }
            JSONObject errorMessageJson = new JSONObject();
            errorMessageJson.put("message", message);
            responseJson.put("error", errorMessageJson);
        }
        System.out.println(imageUploadVo.getFile().getOriginalFilename());
        return responseJson.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/download-image/{index}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadImageGet(@PathVariable(name = "index") int index,
                                                   HttpServletResponse response) throws IOException {
        ImageDownloadVo imageDownloadVo = new ImageDownloadVo();
        imageDownloadVo.setIndex(index);
        this.boardService.downloadImage(imageDownloadVo);
        if (imageDownloadVo.getResult() == ImageDownloadResult.SUCCESS) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", imageDownloadVo.getMime());
            System.out.println("download");
            return new ResponseEntity<>(imageDownloadVo.getFile(), headers, HttpStatus.OK);
        } else {
            response.sendError(404);
            return null;
        }
    }

    @RequestMapping(value = "/{boardCode}/delete/{articleIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String deleteComment(@PathVariable(name = "boardCode") String boardCode,
                                @PathVariable(name = "articleIndex") int articleIndex,
                                @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                                CommentDeleteVo commentDeleteVo,
                                Model model) {
        commentDeleteVo.setBoardCode(boardCode);
        commentDeleteVo.setArticleIndex(articleIndex);
        this.boardService.deleteComment(commentDeleteVo, userEntity);
        model.addAttribute("commentDeleteResult", commentDeleteVo.getResult());
        return "redirect:/board/" + commentDeleteVo.getBoardCode() + "/read/" + commentDeleteVo.getArticleIndex();
    }

    @RequestMapping(value = "/{boardCode}/modify/{articleIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyGet(@PathVariable(name = "boardCode") String boardCode,
                            @PathVariable(name = "articleIndex") int articleIndex,
                            @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                            ModifyVo modifyVo,
                            Model model) {
        modifyVo.setBoardCode(boardCode);
        modifyVo.setIndex(articleIndex);
        this.boardService.modifyByGet(modifyVo, userEntity);
        model.addAttribute("modifyVo", modifyVo);
        return "board/modify";
    }

    @RequestMapping(value = "/{boardCode}/modify/{articleIndex}",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyPost(@PathVariable(name = "boardCode") String boardCode,
                             @PathVariable(name = "articleIndex") int articleIndex,
                             @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                             ModifyVo modifyVo,
                             Model model) {
        modifyVo.setBoardCode(boardCode);
        modifyVo.setIndex(articleIndex);
        this.boardService.modifyByPost(modifyVo, userEntity);
        model.addAttribute("modifyVo", modifyVo);
        return "redirect:/board/"+ modifyVo.getBoardCode() +"/read/" + modifyVo.getArticleIndex();
    }
}
