package com.example.blogspringboot.service;

import com.example.blogspringboot.entity.Post;

import java.util.List;
public interface PostService {
    Post savePost(Post post);

    List<Post> getAllPosts();
}
