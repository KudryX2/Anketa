package com.anketa.controller;

import com.anketa.dto.QuestionDTO;
import com.anketa.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class QuestionControllerTest {

    @InjectMocks
    QuestionController questionController;

    @Mock
    QuestionService questionService;

    @Test
    public void createQuestionTest_ShouldCreateQuestion(){
        QuestionDTO questionDTO = new QuestionDTO(null, "question", new ArrayList<>());
        Mockito.when(questionService.createQuestion(questionDTO, "survey-ref"))
            .thenReturn("created-question-ref");
        Assertions.assertNotNull(questionController.createQuestion(questionDTO, "survey-ref"));
    }

}
