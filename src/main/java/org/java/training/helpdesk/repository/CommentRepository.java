package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findCommentByArticleId(Long id);
}