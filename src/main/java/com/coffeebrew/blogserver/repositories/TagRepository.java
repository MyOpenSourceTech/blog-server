package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, String> {
    Optional<Tag> findByLabel(String label);
}
