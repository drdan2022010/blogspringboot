package com.example.blogspringboot.service;

import com.example.blogspringboot.entity.Post;

import java.util.List;
public interface PostService {
    Post savePost(Post post);

    List<Post> getAllPosts();
    Post getPostById(Long id);
    public void likePost(Long id);
    public List<Post> searchByName(String keyword);
    public void deletePost(Long id); 
    Post updatePost(Long id, Post post);
    }
