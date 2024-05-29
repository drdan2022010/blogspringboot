package com.example.blogspringboot.service;

import com.example.blogspringboot.entity.Post;
import com.example.blogspringboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceimpl implements PostService{
    @Autowired
    private PostRepository postrepository;

    public Post savePost(Post post){
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(new Date());

        return postrepository.save(post);
    }
}
