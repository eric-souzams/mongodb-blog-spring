package com.mongo.blog.repository;

import com.mongo.blog.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContaining(String title);
    List<Post> findByTitleContainingIgnoreCase(String title);
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchByTitle(String title);

    @Query("{ $and: [{ date: {$gte: ?1} }, { date: {$lte: ?2} }, { $or: [{ 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } }] }] }")
    List<Post> searchByDate(String title, Date min, Date max);

}
