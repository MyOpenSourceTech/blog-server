package com.coffeebrew.blogserver.services;

import com.coffeebrew.blogserver.models.Blog;
import com.coffeebrew.blogserver.repositories.BlogRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}