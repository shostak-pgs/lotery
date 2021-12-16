package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.Article;
import java.util.List;

public interface SearchRepository {
    List<Article> searchArticles(String text);
}