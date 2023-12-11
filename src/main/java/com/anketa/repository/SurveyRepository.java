package com.anketa.repository;

import com.anketa.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Optional<Survey> findByReference(String reference);
}
