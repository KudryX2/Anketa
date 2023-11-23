package com.anketa.service;

import com.anketa.dto.SurveyDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.SurveyMapper;
import com.anketa.model.Survey;
import com.anketa.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyMapper surveyMapper;

    @Override
    public List<SurveyDTO> getList() {
        return surveyRepository.findAll().stream()
            .map(surveyMapper::convertToDTO)
            .toList();
    }

    @Override
    public SurveyDTO getSurvey(String reference) {
        Survey survey = surveyRepository.findByReference(reference)
                .orElseThrow(() -> new BadRequestException("Bad Request: " + reference));
        return surveyMapper.convertToDTO(survey);
    }

    @Override
    public String createSurvey(SurveyDTO surveyDTO) {
        if((Objects.isNull(surveyDTO.name()) ||
            surveyDTO.name().isBlank()) ||
            !surveyDTO.name().matches("[a-zA-Z0-9 ]+"))
                throw new BadRequestException("Bad Request : name is not valid");

        Survey survey = surveyMapper.convertToEntity(surveyDTO);
        return surveyRepository.save(survey).getReference();
    }


}
