package com.coffeebrew.blogserver.controllers;

import com.coffeebrew.blogserver.models.Tag;
import com.coffeebrew.blogserver.services.TagService;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class TagControllerTest {
    EasyRandom random;

    @Mock
    TagService service;

    @InjectMocks
    TagController target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    void shouldReturnTagById() {
        String id = random.nextObject(String.class);
        Tag tag = random.nextObject(Tag.class);
        when(service.getById(id)).thenReturn(Optional.of(tag));

        ResponseEntity<Tag> responseEntity = target.getById(id);

        assertEquals(responseEntity.getBody(), tag);
    }

    @Test
    void shouldReturnNotFoundWhileIdMatchNotFound() {
        String id = random.nextObject(String.class);
        when(service.getById(id)).thenReturn(Optional.empty());

        ResponseEntity<Tag> responseEntity = target.getById(id);

        assertEquals(responseEntity.getBody(), null);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateNewTag() {
        Tag tag = random.nextObject(Tag.class);
        Tag createdTag = random.nextObject(Tag.class);

        when(service.create(tag)).thenReturn(createdTag);

        ResponseEntity<Tag> responseEntity = target.createTag(tag);

        assertEquals(createdTag, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void shouldReturnTagByLabel() {
        String label = random.nextObject(String.class);
        Tag receivedTag = random.nextObject(Tag.class);

        when(service.getByLabel(label)).thenReturn(Optional.of(receivedTag));

        ResponseEntity<Tag> optionalTag = target.getByLabel(label);

        assertEquals(optionalTag.getBody(), receivedTag);
    }
}