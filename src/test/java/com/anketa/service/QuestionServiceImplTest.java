package com.anketa.service;

import com.anketa.dto.QuestionDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.QuestionMapper;
import com.anketa.model.Question;
import com.anketa.model.Survey;
import com.anketa.model.User;
import com.anketa.repository.QuestionRepository;
import com.anketa.repository.SurveyRepository;
import com.anketa.service.validation.ValidationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class QuestionServiceImplTest {

    @InjectMocks
    QuestionServiceImpl questionService;

    @Mock
    ValidationServiceImpl validationService;

    @Mock
    SurveyServiceImpl surveyService;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    QuestionMapper questionMapper;


    private final String questionString = "valid question ?";
    private final String reference = "5e6e750d-f8b1-4288-a4cd-d56627e96a39";

    private final QuestionDTO questionDTO = new QuestionDTO(null, questionString, new ArrayList<>());
    private final Question question = Question.builder()
        .reference(reference)
        .question(questionString)
        .build();


    @Test
    public void getQuestionTest_ShouldRetrieveQuestionByReference(){
        Mockito.when(questionRepository.findByReference(reference))
                .thenReturn(Optional.ofNullable(question));

        Question question = questionService.getQuestion(reference);
        Assertions.assertNotNull(question);
        Assertions.assertEquals(reference, question.getReference());
    }

    @Test
    public void createQuestionTest_ShouldCreateQuestion(){
        Mockito.when(validationService.validateTextField(questionString))
            .thenReturn(true);
        Mockito.when(validationService.validateReference(reference))
            .thenReturn(true);
        Mockito.when(surveyService.getSurvey(reference))
            .thenReturn(Survey.builder().build());
        Mockito.when(questionMapper.convertToEntity(questionDTO))
            .thenReturn(question);
        Mockito.when(questionRepository.save(question))
            .thenReturn(question);

        Assertions.assertEquals(reference, questionService.createQuestion(questionDTO, reference));
    }

    @Test
    public void createQuestionTest_ShouldThrowExceptionNotValidQuestion(){
        Mockito.when(validationService.validateTextField(questionString))
            .thenReturn(false);

        Assertions.assertThrows(BadRequestException.class,
            () -> questionService.createQuestion(questionDTO, reference));
    }

    @Test
    public void createQuestionTest_ShouldThrowExceptionNotValidSurveyReference(){
        Mockito.when(validationService.validateTextField(questionString))
            .thenReturn(true);
        Mockito.when(validationService.validateReference(reference))
            .thenReturn(false);
        Assertions.assertThrows(BadRequestException.class,
            () -> questionService.createQuestion(questionDTO, reference));
    }

}
