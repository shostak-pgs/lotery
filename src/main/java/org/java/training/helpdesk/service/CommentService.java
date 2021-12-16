package org.java.training.helpdesk.service;

import org.java.training.helpdesk.converter.CommentConverter;
import org.java.training.helpdesk.dto.CommentDto;
import org.java.training.helpdesk.entity.Comment;
import org.java.training.helpdesk.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly =  true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    public CommentService(CommentRepository commentRepository, CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
    }

    public List<CommentDto> getCommentsByArticleId(Long articleId) {
        List<CommentDto> dtos = commentRepository.findCommentByArticleId(articleId)
                .stream()
                .map(commentConverter::toDto)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public CommentDto create(CommentDto dto) {
        Comment comment = commentRepository.save(commentConverter.fromDto(dto));
        return commentConverter.toDto(comment);
    }
}