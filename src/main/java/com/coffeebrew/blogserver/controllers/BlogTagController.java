package com.coffeebrew.blogserver.controllers;

import com.coffeebrew.blogserver.models.BlogTag;
import com.coffeebrew.blogserver.services.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/blog_tags")
public class BlogTagController {

    @Autowired
    BlogTagService blogTagService;

    @PostMapping
    public ResponseEntity<BlogTag> save(@RequestBody BlogTag blogTag) {
        return new ResponseEntity<BlogTag>(blogTagService.save(blogTag), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogTag>> getByBlogId(@Param("blogId") String blogId) {
        return new ResponseEntity<List<BlogTag>>(blogTagService.getByBlogId(blogId), HttpStatus.CREATED);
    }
}
