package com.mongo.blog.resource;

import com.mongo.blog.domain.Post;
import com.mongo.blog.domain.User;
import com.mongo.blog.dto.UserDto;
import com.mongo.blog.services.PostService;
import com.mongo.blog.services.UserService;
import com.mongo.blog.utils.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findUserById(@PathVariable("id") String id) {
        Post post = postService.findById(id);

        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/title-search")
    public ResponseEntity<List<Post>> titleSearch(@RequestParam(value = "search", defaultValue = "") String search) {
        search = URL.decodeParam(search);

        List<Post> posts = postService.findByTitle(search);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping(value = "/full-search")
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "search", defaultValue = "") String search,
                                                 @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                 @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        search = URL.decodeParam(search);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());

        List<Post> posts = postService.searchByDate(search, min, max);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

}
