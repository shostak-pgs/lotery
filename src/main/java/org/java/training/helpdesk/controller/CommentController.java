package org.java.training.helpdesk.controller;

import org.java.training.helpdesk.dto.CommentDto;
import org.java.training.helpdesk.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable final Long id) {
        return ResponseEntity.ok(commentService.getCommentsByArticleId(id));
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@Valid @RequestBody final CommentDto dto) {
        CommentDto created = commentService.create(dto);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }
}