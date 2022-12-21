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
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String email;

    public UserDto(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
    }
}
