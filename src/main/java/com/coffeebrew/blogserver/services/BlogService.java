package com.coffeebrew.blogserver.services;

import com.coffeebrew.blogserver.models.Blog;
import com.coffeebrew.blogserver.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public Optional<Blog> getById(String id) {
        return blogRepository.findById(id);
    }

    public Blog create(Blog blog) {
        return blogRepository.save(blog);
    }

    public Page<Blog> getByPage(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
}
