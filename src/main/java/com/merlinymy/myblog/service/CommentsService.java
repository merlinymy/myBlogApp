package com.merlinymy.myblog.service;

import com.merlinymy.myblog.entity.BlogPosts;
import com.merlinymy.myblog.entity.Comments;
import com.merlinymy.myblog.payload.CommentsDto;

public interface CommentsService {
    CommentsDto createComment(long blogPostId, CommentsDto commentsDto);
}
