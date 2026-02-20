package org.java.training.helpdesk.controller;

import org.java.training.helpdesk.service.LikeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeeController {

    private final LikeeService likeeService;

    public LikeeController(LikeeService likeeService) {
        this.likeeService = likeeService;
    }

    @PostMapping
    public ResponseEntity<Void> toggleLike(@RequestBody final Long articleId) {
        likeeService.toggleLike(articleId);
        return ResponseEntity.ok().build();
    }
}