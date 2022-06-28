package com.merlinymy.myblog.service.implementation;

import com.merlinymy.myblog.entity.BlogPosts;
import com.merlinymy.myblog.exception.ResourceNotFoundException;
import com.merlinymy.myblog.payload.BlogPostResponse;
import com.merlinymy.myblog.payload.BlogPostsDto;
import com.merlinymy.myblog.repository.PostsRepository;
import com.merlinymy.myblog.service.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BlogPostServiceImpl implements BlogPostsService {

    //field injection is not recommended
    private PostsRepository postsRepository;

    // constructor injection
    @Autowired
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
    public BlogPostResponse getAllPosts(int pageSize, int pageNo, String sortBy, String sortMethod) {
        // sort instance that is dynamic
        Sort sort = sortMethod.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<BlogPosts> allPosts = postsRepository.findAll(pageable);

        // get content from page object
        List<BlogPosts> listOfPosts = allPosts.getContent();
        List<BlogPostsDto> content =  listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());

        BlogPostResponse blogPostResponse = new BlogPostResponse();
        blogPostResponse.setPageNo(allPosts.getNumber());
        blogPostResponse.setPageSize(allPosts.getSize());
        blogPostResponse.setContent(content);
        blogPostResponse.setTotalElements(allPosts.getTotalElements());
        blogPostResponse.setTotalPage(allPosts.getTotalPages());
        blogPostResponse.setLast(allPosts.isLast());
        blogPostResponse.setSort(allPosts.getSort());
        return blogPostResponse;
    }

    @Override
    public BlogPostsDto getPostById(long id) {
        BlogPosts onePost = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(onePost);
    }

    // update post by id
    @Override
    public BlogPostsDto updatePostById(long id, BlogPostsDto blogPostsDto) {
        // pull the post we want to change from the database
        BlogPosts postToUpdate = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        // update to what we have from blogPostsDto (new contents)
        postToUpdate.setContent(blogPostsDto.getContent());
        postToUpdate.setTitle(blogPostsDto.getTitle());
        postToUpdate.setDescription(blogPostsDto.getDescription());

        BlogPosts updatedPost = postsRepository.save(postToUpdate);
        return mapToDTO(updatedPost);

    }

    // delete post by id
    @Override
    public void deletePostById(long id) {
        BlogPosts post = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postsRepository.delete(post);
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
