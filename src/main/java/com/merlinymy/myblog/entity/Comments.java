package com.merlinymy.myblog.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;
    String name;
    String email;
    //many comments to one post
    @ManyToOne(fetch = FetchType.LAZY)
    // foreign key is blog_id
    @JoinColumn(name = "blog_id", nullable = false)
    private BlogPosts blogPosts;

}
