package com.merlinymy.myblog.controller;

import com.merlinymy.myblog.payload.BlogPostsDto;
import com.merlinymy.myblog.service.BlogPostsService;
import com.merlinymy.myblog.service.implementation.BlogPostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class BlogPostsController {
    private BlogPostsService blogPostsService;

    public BlogPostsController(BlogPostsService blogPostsService) {
        this.blogPostsService = blogPostsService;
    }
    @PostMapping
    public ResponseEntity<BlogPostsDto> createBlogPost(@RequestBody BlogPostsDto blogPostsDto) {
        BlogPostsDto newPost = blogPostsService.createPost(blogPostsDto);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
