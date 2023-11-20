package com.anketa.repository;

import com.anketa.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnketaRepository extends JpaRepository<Survey, Long> {}
