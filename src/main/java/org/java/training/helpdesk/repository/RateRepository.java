package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
}