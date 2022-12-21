package com.mongo.blog.domain;

import com.mongo.blog.dto.AuthorDto;
import com.mongo.blog.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Document(collection = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private Date date;
    @Field("title")
    private String title;
    @Field("body")
    private String body;
    private AuthorDto author;
    private List<CommentDto> comments = new ArrayList<>();

    public Post(String id, Date date, String title, String body, AuthorDto author) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
