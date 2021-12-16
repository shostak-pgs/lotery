package org.java.training.helpdesk.converter;

import org.java.training.helpdesk.dto.CommentDto;
import org.java.training.helpdesk.entity.Comment;
import org.java.training.helpdesk.service.ArticleService;
import org.java.training.helpdesk.service.AuthenticationService;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class CommentConverter {

    private final ArticleService articleService;
    private final AuthenticationService authenticationService;
    private final UserConverter userConverter;

    public CommentConverter(ArticleService articleService, AuthenticationService authenticationService,
                            UserConverter userConverter) {
        this.articleService = articleService;
        this.authenticationService = authenticationService;
        this.userConverter = userConverter;
    }

    public CommentDto toDto(final Comment entity) {
        return new CommentDto(entity.getId(), userConverter.toDto(entity.getUser()), entity.getText(),
                entity.getCreatedDate(), entity.getArticle().getId());
    }

    public Comment fromDto(final CommentDto dto) {
        return new Comment(authenticationService.getAuthenticateUser(), dto.getText(),
                new Date(), articleService.getArticle(dto.getArticleId()));
    }
}