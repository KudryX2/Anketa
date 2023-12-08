package com.anketa.service;

import com.anketa.dto.AnswerDTO;

public interface AnswerService {
    public String createAnswer(AnswerDTO answerDTO, String questionReference);
}
