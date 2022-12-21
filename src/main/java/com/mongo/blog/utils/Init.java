package com.mongo.blog.utils;

import com.mongo.blog.domain.Post;
import com.mongo.blog.domain.User;
import com.mongo.blog.dto.AuthorDto;
import com.mongo.blog.dto.CommentDto;
import com.mongo.blog.repository.PostRepository;
import com.mongo.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Init implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        User user = new User(null, "Marcos", "marcos@gmail.com");
        userRepository.save(user);

        Post post = new Post(null, simpleDateFormat.parse("04/12/2022"), "Partiu EUA",
                "Mermão to indo pros States", new AuthorDto(user));

        CommentDto comment1 = new CommentDto("Good Viagem 1", simpleDateFormat.parse("04/12/2032"), new AuthorDto(user));
        CommentDto comment2 = new CommentDto("Good Viagem 2", simpleDateFormat.parse("04/12/2032"), new AuthorDto(user));
        CommentDto comment3 = new CommentDto("Good Viagem 3", simpleDateFormat.parse("04/12/2032"), new AuthorDto(user));

        post.getComments().addAll(List.of(comment1, comment2, comment3));

        postRepository.save(post);

        user.getPosts().add(post);
        userRepository.save(user);
    }
}
