package com.coffeebrew.blogserver.controllers;

import com.coffeebrew.blogserver.models.Blog;
import com.coffeebrew.blogserver.models.Tag;
import com.coffeebrew.blogserver.services.BlogService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

}