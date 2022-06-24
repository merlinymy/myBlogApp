package com.merlinymy.myblog.service;

import com.merlinymy.myblog.payload.BlogPostsDto;

public interface BlogPostsService {
    BlogPostsDto createPost(BlogPostsDto blogPostsDto);
}
