package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TagRepository extends CrudRepository<Tag, String> {
}
