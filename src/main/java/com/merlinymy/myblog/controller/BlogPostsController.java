package com.merlinymy.myblog.controller;

import com.merlinymy.myblog.entity.BlogPosts;
import com.merlinymy.myblog.payload.BlogPostResponse;
import com.merlinymy.myblog.payload.BlogPostsDto;
import com.merlinymy.myblog.service.BlogPostsService;
import com.merlinymy.myblog.service.implementation.BlogPostServiceImpl;
import com.merlinymy.myblog.utils.DefaultValues;
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
    public ResponseEntity<BlogPostResponse> getAllPosts(
            // no hard coded defaultValues
            @RequestParam(value = "pageSize", defaultValue = DefaultValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "pageNo", defaultValue = DefaultValues.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = DefaultValues.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortMethod", defaultValue = DefaultValues.DEFAULT_SORT_METHOD, required = false) String sortMethod
    ) {
        BlogPostResponse allPosts = blogPostsService.getAllPosts(pageSize, pageNo, sortBy, sortMethod);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    // get a post by id
    @GetMapping("/{id}")
    public ResponseEntity<BlogPostsDto> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(blogPostsService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostsDto> updateBlogPostById(@RequestBody BlogPostsDto blogPostsDto, @PathVariable(name = "id") long id) {
        return new ResponseEntity<> (blogPostsService.updatePostById(id, blogPostsDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPostById(@PathVariable long id) {
        blogPostsService.deletePostById(id);
        return new ResponseEntity<>("Post delete successfully.", HttpStatus.OK);
    }

}
