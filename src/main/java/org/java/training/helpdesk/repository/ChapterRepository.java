package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Set<Chapter> findChapterByArticleId(Long id);
}