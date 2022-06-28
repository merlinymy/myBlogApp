package com.merlinymy.myblog.payload;

import com.merlinymy.myblog.entity.BlogPosts;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@NoArgsConstructor
public class BlogPostResponse {
    private List<BlogPostsDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPage;
    private boolean isLast;
    private Sort sort;
}
