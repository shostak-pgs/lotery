package org.java.training.helpdesk.service;

import org.java.training.helpdesk.converter.RateConverter;
import org.java.training.helpdesk.dto.RateDto;
import org.java.training.helpdesk.entity.Rate;
import org.java.training.helpdesk.repository.RateRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RateService {

    private final RateRepository rateRepository;
    private final RateConverter rateConverter;
    private final ArticleService articleService;

    public RateService(RateRepository rateRepository, RateConverter rateConverter, @Lazy ArticleService articleService) {
        this.rateRepository = rateRepository;
        this.rateConverter = rateConverter;
        this.articleService = articleService;
    }

    public RateDto create(RateDto rateDto) {
        Rate rate = rateConverter.fromDto(rateDto);
        rate.setArticle(articleService.getArticle(rateDto.getArticleDto().getId()));
        return rateConverter.toDto(rateRepository.save(rate));
    }
}