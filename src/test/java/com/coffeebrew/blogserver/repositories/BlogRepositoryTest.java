package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.Blog;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class BlogRepositoryTest {
    EasyRandom random;

    @Autowired
    BlogRepository target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    public void shouldStoreBlogInDatabase() {
        Blog blog = random.nextObject(Blog.class);

        Blog savedBlog = target.save(blog);

        final Optional<Blog> fetchedBlog = target.findById(savedBlog.getId());

        assertEquals(fetchedBlog.get().getBody(), savedBlog.getBody());
        assertEquals(fetchedBlog.get().getTitle(), savedBlog.getTitle());
        assertEquals(fetchedBlog.get().getCreatedBy(), savedBlog.getCreatedBy());
    }
}
