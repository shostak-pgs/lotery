package org.java.training.helpdesk.converter;

import org.java.training.helpdesk.dto.ArticleDto;
import org.java.training.helpdesk.entity.Article;
import org.java.training.helpdesk.service.AuthenticationService;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {

    private final AuthenticationService authenticationService;
    private final RateConverter rateConverter;

    public ArticleConverter(AuthenticationService authenticationService, RateConverter rateConverter) {
        this.authenticationService = authenticationService;
        this.rateConverter = rateConverter;
    }

    public ArticleDto toDto(final Article entity) {
        return new ArticleDto(entity.getId(), entity.getName(), entity.getGenre(), entity.getDescription(),
                entity.getTags(), entity.getRates().stream().map(rateConverter::toDto).collect(Collectors.toList()),
                entity.getLastModifiedDate(), entity.getUser().getId());
    }

    public Article fromDto(final ArticleDto dto) {
        return new Article(dto.getId(), dto.getName(), dto.getDescription(), authenticationService.getAuthenticateUser(),
                dto.getGenre(), dto.getTags(), new Date());
    }
}