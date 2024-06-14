package com.example.blogspringboot.controller;

import com.example.blogspringboot.entity.Comment;
import com.example.blogspringboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins ="*")

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public Comment createComment(@RequestParam Long postId, @RequestParam String postedBy, @RequestParam String content) {
        return commentService.createComment(postId, postedBy, content);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Long postId) {
        try {
            return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}