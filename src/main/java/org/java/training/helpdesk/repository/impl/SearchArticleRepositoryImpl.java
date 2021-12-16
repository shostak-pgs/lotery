package org.java.training.helpdesk.repository.impl;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.java.training.helpdesk.entity.Article;
import org.java.training.helpdesk.entity.enums.Genre;
import org.java.training.helpdesk.entity.enums.Tag;
import org.java.training.helpdesk.repository.SearchRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SearchArticleRepositoryImpl implements SearchRepository {
    private static final String[] FIELDS_ARRAY = new String[]{"name", "description", "chapters.name", "comments.text",
            "chapters.content",  "user.firstName", "user.lastName"};
    private static final String GENRE_FIELD = "genre";
    private static final String TAGS_FIELD = "tags";

    @PersistenceContext
    private EntityManager entityManager;

    public List<Article> searchArticles(String text) {
        FullTextQuery jpaQuery = searchUsersQuery(text);
        return jpaQuery.getResultList();
    }

    private FullTextQuery searchUsersQuery (String searchText) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Article.class)
                .get();

       Query luceneQuery = queryBuilder.bool()
                .should(queryBuilder.keyword().onFields(FIELDS_ARRAY).matching(searchText).createQuery())
                .should(queryBuilder.keyword().onField(GENRE_FIELD).matching(Genre.fromString(searchText)).createQuery())
                .should(queryBuilder.keyword().onField(TAGS_FIELD).matching(Tag.fromString(searchText)).createQuery())
                .createQuery();

        return fullTextEntityManager.createFullTextQuery(luceneQuery, Article.class);
    }
}