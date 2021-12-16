package org.java.training.helpdesk.config.search;

import javax.persistence.*;
import javax.transaction.*;
import org.hibernate.search.jpa.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.*;

@Component
public class HibernateSearchInit implements ApplicationListener<ContextRefreshedEvent> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            throw new RuntimeException("Error occured trying to build Hibernate Search indexes "
                    + e.toString());
        }
    }
}