package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.Likee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeeRepository extends JpaRepository<Likee, Long> {
    Likee findLikeeByChapterIdAndUserId(Long chapterId, Long userId);
}