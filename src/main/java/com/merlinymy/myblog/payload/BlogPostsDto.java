package com.merlinymy.myblog.payload;

import lombok.Data;

@Data
public class BlogPostsDto {
    private long Id;
    private String title;
    private String content;
    private String description;
}
