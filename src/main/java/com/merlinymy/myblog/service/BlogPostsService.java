package com.merlinymy.myblog.service;

import com.merlinymy.myblog.payload.BlogPostsDto;

import java.util.List;

public interface BlogPostsService {
    BlogPostsDto createPost(BlogPostsDto blogPostsDto);

    List<BlogPostsDto> getAllPosts();

    BlogPostsDto getPostById(long id);
}
