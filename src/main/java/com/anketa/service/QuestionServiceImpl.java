package com.anketa.service;

import com.anketa.constants.ErrorMessages;
import com.anketa.dto.QuestionDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.exception.NotFoundException;
import com.anketa.mapper.QuestionMapper;
import com.anketa.model.Question;
import com.anketa.model.Survey;
import com.anketa.repository.QuestionRepository;
import com.anketa.service.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    ValidationService validationService;

    @Autowired
    SurveyServiceImpl surveyService;

    @Override
    public Question getQuestion(String reference){
        return questionRepository.findByReference(reference)
            .orElseThrow(() -> new NotFoundException(ErrorMessages.QUESTION_NOT_FOUND));
    }

    @Override
    public String createQuestion(QuestionDTO questionDTO, String surveyReference) {
        if(!validationService.validateTextField(questionDTO.question()))
            throw new BadRequestException(ErrorMessages.QUESTION_NOT_VALID);
        if(!validationService.validateReference(surveyReference))
            throw new BadRequestException(ErrorMessages.SURVEY_REFERENCE_NOT_VALID);

        Survey survey = surveyService.getSurvey(surveyReference);

        Question question = questionMapper.convertToEntity(questionDTO);
        question.setSurvey(survey);

        return questionRepository.save(question).getReference();
    }
}
