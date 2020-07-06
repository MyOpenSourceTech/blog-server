package com.coffeebrew.blogserver.services;

import com.coffeebrew.blogserver.models.BlogTag;
import com.coffeebrew.blogserver.repositories.BlogTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTagService {

    @Autowired
    BlogTagRepository blogTagRepository;

    public BlogTag save(BlogTag blogTag) {
        return blogTagRepository.save(blogTag);
    }

    public List<BlogTag> getByBlogId(String blogId) {
        return blogTagRepository.findByBlogId(blogId);
    }
}
