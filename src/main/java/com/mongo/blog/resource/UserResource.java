package com.mongo.blog.resource;

import com.mongo.blog.domain.Post;
import com.mongo.blog.domain.User;
import com.mongo.blog.dto.UserDto;
import com.mongo.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<User> users = userService.findAll();

        List<UserDto> usersDto = users.stream().map(UserDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(usersDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") String id) {
        User user = userService.findById(id);
        UserDto userDto = new UserDto(user);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> userPosts(@PathVariable("id") String id) {
        User user = userService.findById(id);

        return ResponseEntity.ok(user.getPosts());
    }

    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserDto userDto) {
        User user = userService.fromDto(userDto);
        user = userService.insert(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        User user = userService.fromDto(userDto);
        user.setId(id);

        userService.update(user);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
