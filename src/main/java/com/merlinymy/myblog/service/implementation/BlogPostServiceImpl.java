package com.merlinymy.myblog.service.implementation;

import com.merlinymy.myblog.entity.BlogPosts;
import com.merlinymy.myblog.exception.ResourceNotFoundException;
import com.merlinymy.myblog.payload.BlogPostsDto;
import com.merlinymy.myblog.repository.PostsRepository;
import com.merlinymy.myblog.service.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BlogPostServiceImpl implements BlogPostsService {

    //field injection is not recommended
    private PostsRepository postsRepository;

    // constructor injection
    public BlogPostServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public BlogPostsDto createPost(BlogPostsDto blogPostsDto) {

        // Convert DTO to Entity
        BlogPosts blogPostsEntity = mapToEntity(blogPostsDto);

        // Save the entity to generate the id
        BlogPosts newPost = postsRepository.save(blogPostsEntity);

        // we want to return the newPost to controller as a DTO
        // convert newPost JPA entity to DTO

        return mapToDTO(newPost);
    }

    @Override
    public List<BlogPostsDto> getAllPosts() {
        List<BlogPosts> allPosts = postsRepository.findAll();
        return allPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public BlogPostsDto getPostById(long id) {
        BlogPosts onePost = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(onePost);
    }

    private BlogPostsDto mapToDTO(BlogPosts newPost) {
        BlogPostsDto postResponse = new BlogPostsDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }

    private BlogPosts mapToEntity(BlogPostsDto blogPostsDto) {
        BlogPosts blogPosts = new BlogPosts();
        // transfer DTO to JPA entity
        blogPosts.setTitle(blogPostsDto.getTitle());
        blogPosts.setContent(blogPostsDto.getContent());
        blogPosts.setDescription(blogPosts.getDescription());
        return blogPosts;
    }
}
