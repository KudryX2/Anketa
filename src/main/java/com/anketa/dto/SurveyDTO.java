package com.anketa.dto;

import java.util.List;

public record SurveyDTO(String reference, String name, List<QuestionDTO> questionList){}
