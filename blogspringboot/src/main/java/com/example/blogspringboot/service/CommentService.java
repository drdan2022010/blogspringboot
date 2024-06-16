package com.example.blogspringboot.service;

import com.example.blogspringboot.entity.Comment;
import java.util.List;

public interface CommentService {

    Comment createComment(Long postId, String postedBy, String content);
    public List<Comment> getCommentsByPostId(Long postId);
    }