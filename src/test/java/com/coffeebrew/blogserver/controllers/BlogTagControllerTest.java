package com.coffeebrew.blogserver.controllers;

import com.coffeebrew.blogserver.models.BlogTag;
import com.coffeebrew.blogserver.services.BlogTagService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BlogTagControllerTest {
    EasyRandom random;

    @Mock
    BlogTagService blogTagService;

    @InjectMocks
    BlogTagController target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    public void shouldAddTagToABlog() {
        BlogTag blogTag = random.nextObject(BlogTag.class);

        when(blogTagService.save(blogTag)).thenReturn(blogTag);

        ResponseEntity<BlogTag> savedBlogTag = target.save(blogTag);

        assertEquals(blogTag, savedBlogTag.getBody());
        assertEquals(HttpStatus.CREATED, savedBlogTag.getStatusCode());
    }

    @Test
    public void shouldGetBlogTagByBlogId() {
        String blogId = random.nextObject(String.class);
        List<BlogTag> blogTag = random.nextObject(ArrayList.class);

        when(blogTagService.getByBlogId(blogId)).thenReturn(blogTag);

        ResponseEntity<List<BlogTag>> blogTags = target.getByBlogId(blogId);

        assertEquals(blogTag, blogTags.getBody());
    }
}