package com.mongo.blog.services;

import com.mongo.blog.domain.Post;
import com.mongo.blog.exception.ObjectNotFoundException;
import com.mongo.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }

    public List<Post> searchByDate(String text, Date min, Date max) {
        max = new Date(max.getTime() + 24 * 60 * 60 * 1000);

        return postRepository.searchByDate(text, min, max);
    }

}
