package org.java.training.helpdesk.service;

import org.java.training.helpdesk.converter.ArticleConverter;
import org.java.training.helpdesk.dto.ArticleDto;
import org.java.training.helpdesk.dto.ChapterDto;
import org.java.training.helpdesk.entity.Article;
import org.java.training.helpdesk.entity.User;
import org.java.training.helpdesk.entity.enums.Role;
import org.java.training.helpdesk.exception.NotFoundException;
import org.java.training.helpdesk.exception.NotOwnerException;
import org.java.training.helpdesk.repository.ArticleRepository;
import org.java.training.helpdesk.repository.SearchRepository;
import org.java.training.helpdesk.utils.CopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;
    private final ChapterService chapterService;
    private final SearchRepository searchRepository;
    private final AuthenticationService authenticationService;

    public ArticleService(ArticleRepository articleRepository, ArticleConverter articleConverter, ChapterService chapterService, SearchRepository searchRepository, AuthenticationService authenticationService) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.chapterService = chapterService;
        this.searchRepository = searchRepository;
        this.authenticationService = authenticationService;
    }

    public Set<ArticleDto> getArticles() {
        return articleRepository.findAll()
                .stream()
                .map(articleConverter::toDto)
                .collect(Collectors.toSet());
    }

    public Article getArticle(Long id) {
       return articleRepository.findById(id).orElseThrow(() -> new NotFoundException(Article.class, id));
    }

    public Set<ArticleDto> getArticlesByUserId(Long id) {
        return articleRepository.findByUserId(id)
                .stream()
                .map(articleConverter::toDto)
                .collect(Collectors.toSet());
    }

    public ArticleDto getArticleDto(Long id) {
        return articleConverter.toDto(getArticle(id));
    }

    public List<ChapterDto> getChaptersByArticleId(Long id) {
        return chapterService.getChaptersByArticleId(id);
    }

    @Transactional
    public Set<ArticleDto> deleteArticle(Long id) {
        Article article =getArticle(id);
        isOwner(article);
        articleRepository.deleteById(id);
        return getArticles();
    }

    @Transactional
    public ArticleDto create(ArticleDto dto) {
        Article article = articleConverter.fromDto(dto);
        return articleConverter.toDto(articleRepository.save(article));
    }

    @Transactional
    public ArticleDto update(ArticleDto dto) {
        Article article = articleRepository.getOne(dto.getId());
        isOwner(article);
        CopyUtils.copyNonNullProperties(dto, article);
        article.setLastModifiedDate(new Date());
        return articleConverter.toDto(articleRepository.save(article));
    }

    @Transactional
    public void setLastModifiedDate(Long id) {
        Article article = getArticle(id);
        article.setLastModifiedDate(new Date());
        articleRepository.save(article);
    }

    public void isOwner(Article article) {
        User user = authenticationService.getAuthenticateUser();
        if(!user.getRole().equals(Role.ADMIN)) {
            if (!(user.equals(article.getUser()))) {
                throw new NotOwnerException(authenticationService.getAuthenticateUser());
            }
        }
    }

    @Transactional
    public Set<ArticleDto> searchArticles(String text) {
        List<Article> articles = searchRepository.searchArticles(text);
        return articles
                .stream()
                .map(articleConverter::toDto)
                .collect(Collectors.toSet());
    }
}