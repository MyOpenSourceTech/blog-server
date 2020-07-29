package com.coffeebrew.blogserver.repositories;

import com.coffeebrew.blogserver.models.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, String> {
}
