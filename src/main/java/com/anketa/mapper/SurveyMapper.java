package com.anketa.mapper;

import com.anketa.dto.SurveyDTO;
import com.anketa.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SurveyMapper {

    @Autowired
    private QuestionMapper questionMapper;

    public SurveyDTO convertToDTO(Survey survey){
        return new SurveyDTO(
            survey.getReference(),
            survey.getName(),
            survey.getQuestions().stream()
                .map(questionMapper::convertToDTO)
                .toList()
        );
    }

    public Survey convertToEntity(SurveyDTO surveyDTO){
        return Survey.builder()
                .reference(String.valueOf(UUID.randomUUID()))
                .name(surveyDTO.name())
                .build();
    }
}
