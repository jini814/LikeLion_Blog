package org.example.blogproject.repository;

import org.example.blogproject.domain.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
