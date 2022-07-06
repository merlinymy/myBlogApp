package com.merlinymy.myblog.repository;

import com.merlinymy.myblog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments, Long> {
}
