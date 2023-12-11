package com.anketa.service;

import com.anketa.constants.ErrorMessages;
import com.anketa.dto.SurveyDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.exception.NotFoundException;
import com.anketa.mapper.SurveyMapper;
import com.anketa.model.Survey;
import com.anketa.repository.SurveyRepository;
import com.anketa.service.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private ValidationService validationService;


    @Override
    public List<SurveyDTO> getList() {
        return surveyRepository.findAll().stream()
            .map(surveyMapper::convertToDTO)
            .toList();
    }

    @Override
    public Survey getSurvey(String reference){
        return surveyRepository.findByReference(reference)
            .orElseThrow(() -> new NotFoundException(ErrorMessages.SURVEY_NOT_FOUND));
    }

    @Override
    public SurveyDTO getSurveyDTO(String reference) {
        if(!validationService.validateReference(reference))
            throw new BadRequestException(ErrorMessages.SURVEY_REFERENCE_NOT_VALID);
        Survey survey = getSurvey(reference);
        return surveyMapper.convertToDTO(survey);
    }

    @Override
    public String createSurvey(SurveyDTO surveyDTO) {
        if(!validationService.validateTextField(surveyDTO.name()))
            throw new BadRequestException(ErrorMessages.SURVEY_NAME_NOT_VALID);
        Survey survey = surveyMapper.convertToEntity(surveyDTO);
        return surveyRepository.save(survey).getReference();
    }

    @Override
    public void deleteSurvey(String reference) {
        if(!validationService.validateReference(reference))
            throw new BadRequestException(ErrorMessages.SURVEY_REFERENCE_NOT_VALID);
        Survey surveyToDelete = surveyRepository.findByReference(reference)
            .orElseThrow(() -> new NotFoundException(ErrorMessages.SURVEY_NOT_FOUND));
        surveyRepository.delete(surveyToDelete);
    }

}
