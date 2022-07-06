package com.merlinymy.myblog.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor

@Entity
@Table(
        name = "blogPosts"
)
public class BlogPosts {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private String description;
    @OneToMany(mappedBy = "blogPosts", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comments> comments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BlogPosts blogPosts = (BlogPosts) o;
        return id != null && Objects.equals(id, blogPosts.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
