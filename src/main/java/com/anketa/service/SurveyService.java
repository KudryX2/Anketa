package com.anketa.service;

import com.anketa.dto.SurveyDTO;
import com.anketa.model.Survey;

import java.util.List;

public interface SurveyService {

    List<SurveyDTO> getList();

    Survey getSurvey(String reference);

    SurveyDTO getSurveyDTO(String reference);

    String createSurvey(SurveyDTO surveyDTO);

    void deleteSurvey(String reference);
}
