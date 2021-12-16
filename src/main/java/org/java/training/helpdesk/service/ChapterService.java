package org.java.training.helpdesk.service;

import org.java.training.helpdesk.converter.ChapterConverter;
import org.java.training.helpdesk.dto.ChapterDto;
import org.java.training.helpdesk.dto.ChapterWrapperDto;
import org.java.training.helpdesk.entity.Chapter;
import org.java.training.helpdesk.entity.User;
import org.java.training.helpdesk.exception.NotFoundException;
import org.java.training.helpdesk.repository.ChapterRepository;
import org.java.training.helpdesk.utils.CopyUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final ChapterConverter chapterConverter;
    private final ArticleService articleService;
    private final AttachmentService attachmentService;

    public ChapterService(ChapterRepository chapterRepository, ChapterConverter chapterConverter,
                          @Lazy ArticleService articleService, AttachmentService attachmentService) {
        this.chapterRepository = chapterRepository;
        this.chapterConverter = chapterConverter;
        this.articleService = articleService;
        this.attachmentService = attachmentService;
    }

    public List<ChapterDto> getChaptersByArticleId(Long articleId) {
        List<ChapterDto> dtos = chapterRepository.findChapterByArticleId(articleId)
                .stream()
                .map(chapterConverter::toDto)
                .collect(Collectors.toList());
        return dtos;
    }

    public Chapter getChapter(Long id) {
        return chapterRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
    }

    public ChapterDto getChapterById(Long id) {
        return chapterConverter.toDto(getChapter(id));
    }

    @Transactional
    public void delete(Long id) {
        articleService.isOwner(chapterRepository.getOne(id).getArticle());
        chapterRepository.deleteById(id);
    }

    @Transactional
    public ChapterDto create(ChapterWrapperDto dto) {
        articleService.isOwner(articleService.getArticle(dto.getArticleId()));
        Chapter chapter = chapterConverter.fromDto(dto);
        if(dto.getFile() != null) {
            chapter.setAttachment(attachmentService.create(dto.getFile()));
        }
        chapter.setArticle(articleService.getArticle(dto.getArticleId()));
        chapter.setLikes(new HashSet<>());
        Chapter created = chapterRepository.save(chapter);
        articleService.setLastModifiedDate(dto.getArticleId());
        return chapterConverter.toDto(created);
    }

    @Transactional
    public ChapterDto update(ChapterWrapperDto dto) {
        Chapter chapter = chapterRepository.getOne(dto.getId());
        articleService.isOwner(chapter.getArticle());
        CopyUtils.copyNonNullProperties(dto, chapter);
        if(dto.getFile() != null) {
            chapter.setAttachment(attachmentService.create(dto.getFile()));
        }
        Chapter updated = chapterRepository.save(chapter);
        articleService.setLastModifiedDate(dto.getArticleId());
        return chapterConverter.toDto(updated);
    }
}