package org.java.training.helpdesk.controller;

import org.hibernate.service.spi.ServiceException;
import org.java.training.helpdesk.dto.ChapterDto;
import org.java.training.helpdesk.dto.ChapterWrapperDto;
import org.java.training.helpdesk.service.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDto> getChapter(@PathVariable final Long id) throws ServiceException {
        return ResponseEntity.ok(chapterService.getChapterById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam final Long id) {
        chapterService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ChapterDto> create(@ModelAttribute ChapterWrapperDto dto) {
        ChapterDto created = chapterService.create(dto);
        final URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(created.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChapterDto> update(@ModelAttribute ChapterWrapperDto dto) {
        ChapterDto updated = chapterService.update(dto);
        return ResponseEntity.ok(updated);
    }
}