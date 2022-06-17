package com.merlinymy.myblog.repository;

import com.merlinymy.myblog.entity.BlogPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<BlogPosts, Long> {

}
