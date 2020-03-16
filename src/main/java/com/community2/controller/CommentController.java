package com.community2.controller;

import com.community2.dto.CommentCreateDTO;
import com.community2.dto.CommentDTO;
import com.community2.dto.ResultDTO;
import com.community2.enums.CommentTypeEnum;
import com.community2.exception.CustomizeErrorCodeCode;
import com.community2.model.Comment;
import com.community2.model.User;
import com.community2.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

//ResponseBody可以将回应自动封装成json
    @ResponseBody
    @RequestMapping(value="/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCodeCode.NO_LOGIN);
        }
        if(commentCreateDTO == null|| StringUtils.isBlank(commentCreateDTO.getContent()))
        {
            return ResultDTO.errorOf(CustomizeErrorCodeCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        commentService.insert(comment);
        return ResultDTO.successOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.ListByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.successOf(commentDTOS);
    }
}
