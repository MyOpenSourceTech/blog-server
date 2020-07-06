package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.BlogTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogTagRepository extends CrudRepository<BlogTag, String> {
    List<BlogTag> findByBlogId(String blogId);
}
