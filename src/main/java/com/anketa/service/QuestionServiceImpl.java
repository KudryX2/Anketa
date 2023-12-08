package com.anketa.service;

import com.anketa.dto.QuestionDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.QuestionMapper;
import com.anketa.model.Question;
import com.anketa.model.Survey;
import com.anketa.repository.QuestionRepository;
import com.anketa.repository.SurveyRepository;
import com.anketa.service.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    ValidationService validationService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SurveyRepository surveyRepository;


    @Override
    public Question getQuestion(String reference){
        return questionRepository.findByReference(reference)
            .orElseThrow(() -> new BadRequestException("Bad Request : question not found"));
    }

    @Override
    public String createQuestion(QuestionDTO questionDTO, String surveyReference) {
        if(!validationService.validateTextField(questionDTO.question()))
            throw new BadRequestException("Bad Request : question is not valid");
        if(!validationService.validateReference(surveyReference))
            throw new BadRequestException("Bad Request : surveyReference is not valid");

        Survey survey = surveyRepository.findByReference(surveyReference)
            .orElseThrow(() -> new BadRequestException("Bad Request : surveyReference is not valid"));

        Question question = questionMapper.convertToEntity(questionDTO);
        question.setSurvey(survey);

        return questionRepository.save(question).getReference();
    }
}
