package com.example.blogspringboot.repository;

import com.example.blogspringboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByNameContaining(String name);
} // post long la kieu du lieu cua primary key
