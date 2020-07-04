package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, String> {
}
