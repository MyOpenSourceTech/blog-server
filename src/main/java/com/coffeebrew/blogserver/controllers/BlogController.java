package com.coffeebrew.blogserver.controllers;

import com.coffeebrew.blogserver.models.Blog;
import com.coffeebrew.blogserver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/blogs")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getById(@PathVariable String id) {
        Optional<Blog> blog = blogService.getById(id);
        return blog.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        Blog createdBlog = blogService.create(blog);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }
}
