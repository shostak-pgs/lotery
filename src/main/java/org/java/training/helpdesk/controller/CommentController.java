package org.java.training.helpdesk.controller;

import org.java.training.helpdesk.dto.CommentDto;
import org.java.training.helpdesk.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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
    public ResponseEntity<CommentDto> create(final HttpServletRequest request,
                                          @Valid @RequestBody final CommentDto dto) throws URISyntaxException {
        CommentDto created = commentService.create(dto);
        return ResponseEntity.ok().location(new URI(request.getRequestURI())).body(created);
    }
}