package com.anketa.dto;

import java.util.List;

public record QuestionDTO(String question, List<AnswerDTO> answerList){}