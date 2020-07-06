package com.coffeebrew.blogserver.services;

import com.coffeebrew.blogserver.models.BlogTag;
import com.coffeebrew.blogserver.repositories.BlogTagRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BlogTagServiceTest {
    EasyRandom random;

    @Mock
    BlogTagRepository blogTagRepository;

    @InjectMocks
    BlogTagService blogTagService;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    public void shouldAddTagToSpecificBlog() {
        BlogTag blogTag = random.nextObject(BlogTag.class);

        blogTagService.save(blogTag);

        verify(blogTagRepository, times(1)).save(blogTag);
    }

    @Test
    public void shouldGetAllTagsForSpecificBlog() {
        String blogId = random.nextObject(String.class);
        List<BlogTag> receivedBlogTags = random.nextObject(ArrayList.class);

        when(blogTagRepository.findByBlogId(blogId)).thenReturn(receivedBlogTags);

        List<BlogTag> blogTags = blogTagService.getByBlogId(blogId);

        assertEquals(receivedBlogTags, blogTags);
    }
}