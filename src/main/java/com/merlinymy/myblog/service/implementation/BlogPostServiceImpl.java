package com.merlinymy.myblog.service.implementation;

import com.merlinymy.myblog.entity.BlogPosts;
import com.merlinymy.myblog.payload.BlogPostsDto;
import com.merlinymy.myblog.repository.PostsRepository;
import com.merlinymy.myblog.service.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogPostServiceImpl implements BlogPostsService {

    @Autowired //field injection is not recommended
    private PostsRepository postsRepository;

    // constructor injection


    @Override
    public BlogPostsDto createPost(BlogPostsDto blogPostsDto) {
        BlogPosts blogPosts = new BlogPosts();
        // transfer DTO to JPA entity
        blogPosts.setTitle(blogPostsDto.getTitle());
        blogPosts.setContent(blogPostsDto.getContent());
        blogPosts.setDescription(blogPosts.getDescription());
        BlogPosts newPost = postsRepository.save(blogPosts);

        // we want to return the newPost to controller as a DTO
        // convert newPost JPA entity to DTO
        BlogPostsDto postResponse = new BlogPostsDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
