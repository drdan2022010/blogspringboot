package com.example.blogspringboot.service;

import com.example.blogspringboot.entity.Post;
import com.example.blogspringboot.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<Post> getAllPosts(){
        return postrepository.findAll();
    }

    public Post getPostById(Long id){
        Optional<Post> post = postrepository.findById(id);
        if(post.isPresent()){
            Post post1 = post.get();
            post1.setViewCount(post1.getViewCount()+1);
            return postrepository.save(post1);
        }else {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        }
    }

    public void deletePost(Long id){
        postrepository.deleteById(id);
    }
    
    public Post updatePost(Long id, Post post) {
        Optional<Post> existingPost = postrepository.findById(id);
        if(existingPost.isPresent()){
            Post postToUpdate = existingPost.get();
            postToUpdate.setName(post.getName());
            postToUpdate.setContent(post.getContent());
            postToUpdate.setPostedBy(post.getPostedBy());
            postToUpdate.setImg(post.getImg());
            postToUpdate.setTags(post.getTags());
            return postrepository.save(postToUpdate);
        } else {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        }
    }

    public void likePost(Long id){
        Optional<Post> post = postrepository.findById(id);
        if(post.isPresent()){
            Post post1 = post.get();
            post1.setLikeCount(post1.getLikeCount()+1);
            postrepository.save(post1);
        }else {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        }
    }
    public List<Post> searchByName(String keyword){
        return postrepository.findAllByNameContaining(keyword);
    }
}