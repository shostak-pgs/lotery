package org.java.training.helpdesk.controller;

import org.hibernate.service.spi.ServiceException;
import org.java.training.helpdesk.dto.ArticleDto;
import org.java.training.helpdesk.dto.ChapterDto;
import org.java.training.helpdesk.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<Set<ArticleDto>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Set<ArticleDto>> delete(@PathVariable final Long id) {
        return ResponseEntity.ok(articleService.deleteArticle(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable final Long id) {
        return ResponseEntity.ok(articleService.getArticleDto(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Set<ArticleDto>> search(@RequestParam final String text) {
        return ResponseEntity.ok(articleService.searchArticles(text));
    }

    @GetMapping("/{id}/chapters")
    public ResponseEntity<List<ChapterDto>> getChaptersByArticleId(@PathVariable final Long id) throws ServiceException {
        return ResponseEntity.ok(articleService.getChaptersByArticleId(id));
    }

    @PostMapping
    public ResponseEntity<ArticleDto> create(@RequestBody final ArticleDto dto){
        log.info("Creating article: {}", dto.getName());
        ArticleDto created = articleService.create(dto);
        final URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(created.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto> update(@RequestBody final ArticleDto dto){
        ArticleDto updated = articleService.update(dto);
              return ResponseEntity.ok(updated);
    }
}