package com.anketa.service;

import com.anketa.dto.QuestionDTO;
import com.anketa.model.Question;

public interface QuestionService {

    Question getQuestion(String reference);
    String createQuestion(QuestionDTO questionDTO, String surveyReference);

}
