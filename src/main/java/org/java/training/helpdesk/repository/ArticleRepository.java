package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Set<Article> findByUserId(Long id);
}