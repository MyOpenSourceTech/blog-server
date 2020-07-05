package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog, String> {
}
