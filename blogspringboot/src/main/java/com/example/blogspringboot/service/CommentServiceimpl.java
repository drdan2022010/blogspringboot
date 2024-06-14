package com.example.blogspringboot.service;

import com.example.blogspringboot.entity.Comment;
import com.example.blogspringboot.entity.Post;
import com.example.blogspringboot.repository.CommentRepository;
import com.example.blogspringboot.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CommentServiceimpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    public Comment createComment(Long postId,String postedBy,String content){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(post.get());
            comment.setPostedBy(postedBy);
            comment.setContent(content);
            comment.setCreatedAt(new java.util.Date());
            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Post with id " + postId + " not found");

    }
    public List<Comment> getCommentsByPostId(Long postId){
        return commentRepository.findByPostId(postId);  }
}
