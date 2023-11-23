package com.anketa.service;

import com.anketa.dto.SurveyDTO;

import java.util.List;

public interface SurveyService {

    List<SurveyDTO> getList();

    SurveyDTO getSurvey(String reference);

    String createSurvey(SurveyDTO surveyDTO);

}
