package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.BlogTag;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class BlogTagRepositoryTest {
    EasyRandom random;

    @Autowired
    BlogTagRepository blogTagRepository;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @AfterEach
    void tearDown() {
        blogTagRepository.deleteAll();
    }

    @Test
    public void shouldSaveBlogTag() {
        BlogTag blogTag = random.nextObject(BlogTag.class);
        BlogTag savedBlogTag = blogTagRepository.save(blogTag);

        Optional<BlogTag> blogTagOptional = blogTagRepository.findById(savedBlogTag.getId());

        assertEquals(blogTagOptional.get().getBlogId(), savedBlogTag.getBlogId());
    }

    @Test
    public void shouldGetBlogTagByBlogId() {
        String blogId = random.nextObject(String.class);
        String tagId1 = random.nextObject(String.class);
        String tagId2 = random.nextObject(String.class);

        BlogTag blogTag1 = new BlogTag(blogId, tagId1);
        BlogTag blogTag2 = new BlogTag(blogId, tagId2);

        BlogTag savedBlogTag1 = blogTagRepository.save(blogTag1);
        BlogTag savedBlogTag2 = blogTagRepository.save(blogTag2);

        List<BlogTag> blogTags = blogTagRepository.findByBlogId(blogId);

        assertEquals(savedBlogTag1.getTagId(), blogTags.get(0).getTagId());
        assertEquals(savedBlogTag1.getBlogId(), blogTags.get(0).getBlogId());
        assertEquals(savedBlogTag2.getTagId(), blogTags.get(1).getTagId());
        assertEquals(savedBlogTag2.getBlogId(), blogTags.get(1).getBlogId());
    }

}
