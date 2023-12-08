package com.anketa.controller;

import com.anketa.dto.AnswerDTO;
import com.anketa.service.AnswerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerControllerTest {

    @InjectMocks
    AnswerController answerController;

    @Mock
    AnswerServiceImpl answerService;

    @Test
    public void createAnswerTest_ShouldCreateAnswer(){
        AnswerDTO answerDTO = new AnswerDTO("ref", "answer", null);
        Mockito.when(answerService.createAnswer(answerDTO, "questionReference"))
            .thenReturn("createdAnswerReference");
        Assertions.assertNotNull(answerController.createAnswer(answerDTO, "questionReference"));
    }

}
