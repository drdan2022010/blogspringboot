package com.example.blogspringboot.controller;

import com.example.blogspringboot.entity.Post;
import com.example.blogspringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postservice;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post){
        try{
            Post createPost = postservice.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPOsts(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postservice.getAllPosts());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> deletePost(@PathVariable Long id){
    try{
        postservice.deletePost(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post newPost){
    try {
        postservice.updatePost(id, newPost);
        return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        try {
            Post post = postservice.getPostById(id);
            return ResponseEntity.ok(post);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/{id}/like")
    public ResponseEntity<?> likePost(@PathVariable Long id){
        try {
            postservice.likePost(id);
            return ResponseEntity.ok(new String[]{"Post liked successfully"});
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Post>> searchByName(@PathVariable String keyword){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(postservice.searchByName(keyword));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
