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

    @Test
    public void convertToDtoTest_ShouldMapSurveyToSurveyDTO(){

        String questionString = "What is your favourite food?";
        String nameString = "Foodie anketa";

        Question question = Question.builder()
                .question(questionString)
                .answers(new ArrayList<>())
                .build();

        QuestionDTO questionDTO = new QuestionDTO(questionString, new ArrayList<>());

        Survey survey = Survey.builder()
                .name(nameString)
                .questions(List.of(question))
                .build();

        Mockito.when(questionMapper.convertToDTO(question)).thenReturn(questionDTO);
        SurveyDTO surveyDTO = surveyMapper.convertToDTO(survey);

        Assertions.assertNotNull(surveyDTO);
        Assertions.assertEquals(nameString, surveyDTO.name());
        Assertions.assertNotNull(surveyDTO.questionList());
    }

}
