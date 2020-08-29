package com.coffeebrew.blogserver.controllers;

import com.coffeebrew.blogserver.models.Tag;
import com.coffeebrew.blogserver.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getById(@PathVariable String id) {
        Optional<Tag> tag = tagService.getById(id);
        return tag.<ResponseEntity<Tag>>map(value -> new ResponseEntity<>(tag.get(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("")
    public ResponseEntity<Tag> getByLabel(@RequestParam("label") String label) {
        Optional<Tag> optionalTag = tagService.getByLabel(label);
        if (optionalTag.isEmpty()) {
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Tag>(optionalTag.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = tagService.create(tag);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

}
