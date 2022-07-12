package com.merlinymy.myblog.payload;

import com.merlinymy.myblog.entity.BlogPosts;
import lombok.Data;

@Data
public class CommentsDto {
    Long id;
    String content;
    String name;
    String email;
}
