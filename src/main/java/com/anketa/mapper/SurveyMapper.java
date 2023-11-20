package com.anketa.mapper;

import com.anketa.dto.SurveyDTO;
import com.anketa.model.Survey;

public class SurveyMapper {
    public static SurveyDTO convertToDto(Survey survey){
        return new SurveyDTO(
            survey.getName(),
            survey.getQuestions().stream()
                .map(QuestionMapper::convertToDTO)
                .toList()
        );
    }
}
