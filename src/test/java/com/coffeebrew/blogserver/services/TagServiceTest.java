package com.coffeebrew.blogserver.services;

import com.coffeebrew.blogserver.models.Tag;
import com.coffeebrew.blogserver.repositories.TagRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TagServiceTest {

    EasyRandom random;

    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagService target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    void shouldGetTagById() {
        String id = random.nextObject(String.class);
        Tag receivedTag = random.nextObject(Tag.class);

        when(tagRepository.findById(id)).thenReturn(Optional.of(receivedTag));

        Optional<Tag> tag = target.getById(id);

        assertEquals(receivedTag, tag.get());
    }

    @Test
    void shouldCreateNewTag() {
        Tag tag = random.nextObject(Tag.class);
        Tag receivedTag = random.nextObject(Tag.class);

        when(tagRepository.save(tag)).thenReturn(receivedTag);

        Tag returnedTag = target.create(tag);

        assertEquals(receivedTag, returnedTag);
    }

    @Test
    void shouldGetTagByLabel() {
        String label = random.nextObject(String.class);
        Tag receivedTag = random.nextObject(Tag.class);

        when(tagRepository.findByLabel(label)).thenReturn(Optional.of(receivedTag));

        Optional<Tag> tag = target.getByLabel(label);

        assertEquals(receivedTag, tag.get());
    }
}