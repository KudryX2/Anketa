package com.anketa.service;

import com.anketa.dto.AnswerDTO;
import com.anketa.dto.UserDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.AnswerMapper;
import com.anketa.model.Answer;
import com.anketa.model.Question;
import com.anketa.model.User;
import com.anketa.repository.AnswerRepository;
import com.anketa.service.validation.ValidationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerServiceImplTest {

    @InjectMocks
    AnswerServiceImpl answerService;

    @Mock
    ValidationServiceImpl validationService;

    @Mock
    QuestionServiceImpl questionService;

    @Mock
    UserServiceImpl userService;

    @Mock
    AnswerMapper answerMapper;

    @Mock
    AnswerRepository answerRepository;

    final String testReference = "5e6e750d-f8b1-4288-a4cd-d56627e96a39";
    final String userReference = "5e6e750d-f8b1-4288-a4cd-d56627e96b39";
    final UserDTO userDTO = new UserDTO(userReference, "name");
    final AnswerDTO answerDTO = new AnswerDTO(null, "answer", userDTO);
    final Answer answer = Answer.builder().reference(testReference).build();

    @Test
    public void createAnswerTest_ShouldCreateNewAnswer(){

        Mockito.when(validationService.validateTextField(answerDTO.answer()))
            .thenReturn(true);
        Mockito.when(validationService.validateReference(answerDTO.user().reference()))
            .thenReturn(true);
        Mockito.when(validationService.validateReference(testReference))
            .thenReturn(true);

        Mockito.when(questionService.getQuestion(testReference))
            .thenReturn(new Question());
        Mockito.when(userService.getUser(testReference))
            .thenReturn(new User());
        Mockito.when(answerMapper.convertToEntity(answerDTO))
            .thenReturn(answer);

        Mockito.when(answerRepository.save(answer))
            .thenReturn(answer);

        answerService.createAnswer(answerDTO, testReference);
    }

    @Test
    public void createAnswerTest_ShouldThrowExceptionAnswerIsNotValid(){
        Mockito.when(validationService.validateTextField(answerDTO.answer()))
            .thenReturn(false);
        Assertions.assertThrows(BadRequestException.class,
            () -> answerService.createAnswer(answerDTO, testReference));
    }

    @Test
    public void createAnswerTest_ShouldThrowExceptionUserReferenceIsNotValid(){
        Mockito.when(validationService.validateTextField(answerDTO.answer()))
            .thenReturn(true);
        Mockito.when(validationService.validateReference(answerDTO.user().reference()))
            .thenReturn(false);
        Assertions.assertThrows(BadRequestException.class,
            () -> answerService.createAnswer(answerDTO, testReference));
    }

    @Test
    public void createAnswerTest_ShouldThrowExceptionQuestionReferenceIsNotValid(){
        Mockito.when(validationService.validateTextField(answerDTO.answer()))
            .thenReturn(true);
        Mockito.when(validationService.validateReference(answerDTO.user().reference()))
            .thenReturn(true);
        Mockito.when(validationService.validateReference(testReference))
            .thenReturn(false);
        Assertions.assertThrows(BadRequestException.class,
            () -> answerService.createAnswer(answerDTO, testReference));
    }

}
