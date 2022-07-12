package com.merlinymy.myblog.service.implementation;

import com.merlinymy.myblog.entity.BlogPosts;
import com.merlinymy.myblog.entity.Comments;
import com.merlinymy.myblog.exception.ResourceNotFoundException;
import com.merlinymy.myblog.payload.CommentsDto;
import com.merlinymy.myblog.repository.CommentsRepo;
import com.merlinymy.myblog.repository.PostsRepository;
import com.merlinymy.myblog.service.CommentsService;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {
    private PostsRepository postsRepository;
    private CommentsRepo commentsRepo;
    public CommentsServiceImpl(PostsRepository postsRepository, CommentsRepo commentsRepo) {
        this.postsRepository = postsRepository;
        this.commentsRepo = commentsRepo;
    }

    @Override
    public CommentsDto createComment(long blogPostId, CommentsDto commentsDto) {
        Comments comments = commentsDtoToEntity(commentsDto);
        BlogPosts blogPosts = postsRepository.findById(blogPostId).orElseThrow(() -> new ResourceNotFoundException("post", "id", blogPostId));
        comments.setBlogPosts(blogPosts);
        Comments savedComment =  commentsRepo.save(comments);
        return commentsEntityToDto(savedComment);
    }

    private CommentsDto commentsEntityToDto(Comments comments) {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setContent(comments.getContent());
        commentsDto.setEmail(comments.getEmail());
        commentsDto.setName(comments.getName());
        commentsDto.setId(comments.getId());
        return commentsDto;
    }

    private Comments commentsDtoToEntity(CommentsDto commentsDto) {
        Comments commentsEntity = new Comments();
        commentsEntity.setContent(commentsDto.getContent());
        commentsEntity.setEmail(commentsDto.getEmail());
        commentsEntity.setName(commentsDto.getName());
        commentsEntity.setId(commentsDto.getId());
        return commentsEntity;
    }
}
