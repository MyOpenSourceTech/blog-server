package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.BlogTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagRepository extends CrudRepository<BlogTag, String> {
    List<BlogTag> findByBlogId(String blogId);
}
