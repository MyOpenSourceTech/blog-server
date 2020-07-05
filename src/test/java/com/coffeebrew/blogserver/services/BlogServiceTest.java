package com.coffeebrew.blogserver.services;

import com.coffeebrew.blogserver.models.Blog;
import com.coffeebrew.blogserver.repositories.BlogRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BlogServiceTest {

    EasyRandom random;

    @Mock
    BlogRepository blogRepository;

    @InjectMocks
    BlogService target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    void shouldGetBlogById() {
        String id = random.nextObject(String.class);
        Blog blog = random.nextObject(Blog.class);

        when(blogRepository.findById(id)).thenReturn(Optional.of(blog));

        Optional<Blog> optionalBlog = target.getById(id);

        assertEquals(blog, optionalBlog.get());
    }

    @Test
    void shouldCreateBlogPost() {
        Blog blog = random.nextObject(Blog.class);
        Blog receivedBlog = random.nextObject(Blog.class);

        when(blogRepository.save(blog)).thenReturn(receivedBlog);

        Blog returnedBlog = target.create(blog);

        assertEquals(receivedBlog, returnedBlog);
    }

    @Test
    void shouldReturnSelectedPageResult() {
        int pageSize = 10;
        int pageNumber = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ArrayList<Blog> blogs = random.nextObject(ArrayList.class);
        Page<Blog> receivedPage = new PageImpl<Blog>(blogs);

        when(blogRepository.findAll(pageable)).thenReturn(receivedPage);

        Page<Blog> returnedPages = target.getByPage(pageable);

        assertEquals(receivedPage, returnedPages);
    }
}