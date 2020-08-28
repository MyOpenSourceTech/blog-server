package com.coffeebrew.blogserver.services;

import com.coffeebrew.blogserver.models.Tag;
import com.coffeebrew.blogserver.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public Optional<Tag> getById(String id) {
        return tagRepository.findById(id);
    }

    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    public Optional<Tag> getByLabel(String label) {
        return tagRepository.findByLabel(label);
    }
}
