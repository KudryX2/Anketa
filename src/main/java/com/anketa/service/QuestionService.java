package com.anketa.service;

import com.anketa.dto.QuestionDTO;

public interface QuestionService {

    String createQuestion(QuestionDTO questionDTO, String surveyReference);

}
