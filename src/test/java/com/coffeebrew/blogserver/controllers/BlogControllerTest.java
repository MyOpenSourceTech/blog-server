package com.coffeebrew.blogserver.controllers;

import com.coffeebrew.blogserver.models.Blog;
import com.coffeebrew.blogserver.services.BlogService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BlogControllerTest {
    EasyRandom random;

    @Mock
    BlogService blogService;

    @InjectMocks
    BlogController target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    void shouldGetBlogById() {
        String id = random.nextObject(String.class);
        Blog blog = random.nextObject(Blog.class);

        when(blogService.getById(id)).thenReturn(Optional.of(blog));

        ResponseEntity<Blog> responseEntity = target.getById(id);

        assertEquals(blog, responseEntity.getBody());
    }

    @Test
    void shouldReturnNotFoundWhileIdMatchNotFound() {
        String id = random.nextObject(String.class);
        when(blogService.getById(id)).thenReturn(Optional.empty());

        ResponseEntity<Blog> responseEntity = target.getById(id);

        assertEquals(responseEntity.getBody(), null);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateBlog() {
        Blog blog = random.nextObject(Blog.class);
        Blog createdBlog = random.nextObject(Blog.class);

        when(blogService.create(blog)).thenReturn(createdBlog);

        ResponseEntity<Blog> responseEntity = target.create(blog);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdBlog, responseEntity.getBody());
    }

    @Test
    void shouldGetSpecifiedPage() {
        int pageSize = 10;
        int pageNumber = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ArrayList<Blog> blogs = random.nextObject(ArrayList.class);
        Page<Blog> receivedPage = new PageImpl<Blog>(blogs);

        when(blogService.getByPage(pageable)).thenReturn(receivedPage);

        ResponseEntity<Page<Blog>> responseEntity = target.getByPage(pageable);

        assertEquals(receivedPage, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}