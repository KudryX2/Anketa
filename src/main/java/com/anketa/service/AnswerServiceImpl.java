package com.anketa.service;

import com.anketa.dto.AnswerDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.AnswerMapper;
import com.anketa.model.Answer;
import com.anketa.model.Question;
import com.anketa.model.User;
import com.anketa.repository.AnswerRepository;
import com.anketa.service.validation.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    ValidationServiceImpl validationService;

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AnswerMapper answerMapper;

    @Override
    public String createAnswer(AnswerDTO answerDTO, String questionReference) {
        if(!validationService.validateTextField(answerDTO.answer()))
            throw new BadRequestException("Bad Request : answer is not valid");
        if(Objects.isNull(answerDTO.user()))
            throw new BadRequestException("Bad Request : user is not valid");
        if(!validationService.validateReference(answerDTO.user().reference()))
            throw new BadRequestException("Bad Request : user reference is not valid");
        if(!validationService.validateReference(questionReference))
            throw new BadRequestException("Bad Request : question reference is not valid");

        Question question = questionService.getQuestion(questionReference);
        User user = userService.getUser(answerDTO.user().reference());

        Answer answer = answerMapper.convertToEntity(answerDTO);
        answer.setQuestion(question);
        answer.setUser(user);

        return answerRepository.save(answer).getReference();
    }

}
