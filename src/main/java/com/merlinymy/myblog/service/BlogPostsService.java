package com.merlinymy.myblog.service;

import com.merlinymy.myblog.entity.BlogPosts;
import com.merlinymy.myblog.payload.BlogPostResponse;
import com.merlinymy.myblog.payload.BlogPostsDto;

import java.util.List;

public interface BlogPostsService {
    BlogPostsDto createPost(BlogPostsDto blogPostsDto);

    BlogPostResponse getAllPosts(int pageSize, int pageNo, String sortBy, String sortMethod);

    BlogPostsDto getPostById(long id);

    // update existing post with id
    BlogPostsDto updatePostById(long id, BlogPostsDto blogPostsDto);

    void deletePostById(long id);
}
