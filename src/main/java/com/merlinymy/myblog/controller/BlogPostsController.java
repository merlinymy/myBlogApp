package com.merlinymy.myblog.controller;

import com.merlinymy.myblog.payload.BlogPostsDto;
import com.merlinymy.myblog.service.BlogPostsService;
import com.merlinymy.myblog.service.implementation.BlogPostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // get all posts api
    @GetMapping
    public List<BlogPostsDto> getAllPosts() {
        return blogPostsService.getAllPosts();
    }

    // get a post by id
    @GetMapping("/{id}")
    public BlogPostsDto getPostById(@PathVariable(name = "id") long id) {
        return blogPostsService.getPostById(id);
    }

    @PutMapping("/{id}")
    public BlogPostsDto updateBlogPostById(@RequestBody BlogPostsDto blogPostsDto, @PathVariable(name = "id") long id) {
        return blogPostsService.updatePostById(id, blogPostsDto);
    }


}
