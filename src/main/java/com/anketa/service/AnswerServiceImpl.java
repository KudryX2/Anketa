package com.anketa.service;

import com.anketa.constants.ErrorMessages;
import com.anketa.dto.AnswerDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.AnswerMapper;
import com.anketa.model.Answer;
import com.anketa.model.Question;
import com.anketa.model.User;
import com.anketa.repository.AnswerRepository;
import com.anketa.service.validation.ValidationServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    ValidationServiceImpl validationService;

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    UserServiceImpl userService;


    @Override
    public String createAnswer(AnswerDTO answerDTO, String questionReference) {
        if(!validationService.validateTextField(answerDTO.answer()))
            throw new BadRequestException(ErrorMessages.ANSWER_NOT_VALID);
        if(!validationService.validateReference(answerDTO.user().reference()))
            throw new BadRequestException(ErrorMessages.USER_REFERENCE_NOT_VALID);
        if(!validationService.validateReference(questionReference))
            throw new BadRequestException(ErrorMessages.QUESTION_REFERENCE_NOT_VALID);

        Question question = questionService.getQuestion(questionReference);
        User user = userService.getUser(answerDTO.user().reference());

        Answer answer = answerMapper.convertToEntity(answerDTO);
        answer.setQuestion(question);
        answer.setUser(user);

        return answerRepository.save(answer).getReference();
    }

}
