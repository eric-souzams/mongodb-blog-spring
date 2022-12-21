package com.mongo.blog.dto;

import com.mongo.blog.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    public AuthorDto(User user) {
        id = user.getId();
        name = user.getName();
    }
}
