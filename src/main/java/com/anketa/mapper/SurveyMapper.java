package com.anketa.mapper;

import com.anketa.dto.SurveyDTO;
import com.anketa.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapper {

    @Autowired
    private QuestionMapper questionMapper;

    public SurveyDTO convertToDTO(Survey survey){
        return new SurveyDTO(
            survey.getName(),
            survey.getQuestions().stream()
                .map(questionMapper::convertToDTO)
                .toList()
        );
    }
}
