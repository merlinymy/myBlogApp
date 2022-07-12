package com.merlinymy.myblog.controller;

import com.merlinymy.myblog.payload.CommentsDto;
import com.merlinymy.myblog.service.implementation.CommentsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentsController {
    private CommentsServiceImpl commentsService;

    public CommentsController(CommentsServiceImpl commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentsDto> createComment(@PathVariable("postId") long blogPostId,
                                                     @RequestBody CommentsDto commentsDto) {
        return new ResponseEntity<>(commentsService.createComment(blogPostId, commentsDto), HttpStatus.CREATED);
    }
}
