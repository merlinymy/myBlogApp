package com.merlinymy.myblog.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String name;
    private String email;
    //many comments to one post
    @ManyToOne(fetch = FetchType.LAZY) // LAZY tells Hibernate to only fetch the
    // related entities from the database when you use the relationship

    // foreign key is blog_id
    @JoinColumn(name = "blog_id", nullable = false)
    @ToString.Exclude// to specify the foreinKey
    private BlogPosts blogPosts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comments comments = (Comments) o;
        return id != null && Objects.equals(id, comments.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
