package com.anketa.mapper;

import com.anketa.dto.QuestionDTO;
import com.anketa.dto.SurveyDTO;
import com.anketa.model.Question;
import com.anketa.model.Survey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SurveyMapperTest {

    @InjectMocks
    private SurveyMapper surveyMapper;

    @Mock
    private QuestionMapper questionMapper;

    private final String surveyName = "Foodie anketa";
    private final String reference = "5fc88c59-d88a-4c10-b1a1-dbfd8efb0b67";

    @Test
    public void convertToDtoTest_ShouldMapSurveyToSurveyDTO(){


        String questionString = "What is your favourite food?";

        Question question = Question.builder()
                .question(questionString)
                .answers(new ArrayList<>())
                .build();

        QuestionDTO questionDTO = new QuestionDTO(reference, questionString, new ArrayList<>());

        Survey survey = Survey.builder()
                .name(surveyName)
                .questions(List.of(question))
                .build();

        Mockito.when(questionMapper.convertToDTO(question)).thenReturn(questionDTO);
        SurveyDTO surveyDTO = surveyMapper.convertToDTO(survey);

        Assertions.assertNotNull(surveyDTO);
        Assertions.assertEquals(surveyName, surveyDTO.name());
        Assertions.assertNotNull(surveyDTO.questionList());
    }

    @Test
    public void convertToEntity_ShouldMapSurveyDTOToSurvey(){
        SurveyDTO surveyDTO = new SurveyDTO(reference, surveyName, new ArrayList<>());

        Survey survey = surveyMapper.convertToEntity(surveyDTO);

        Assertions.assertNotNull(survey);
        Assertions.assertEquals(surveyName, surveyDTO.name());
    }

}