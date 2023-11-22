package com.anketa.service;

import com.anketa.dto.SurveyDTO;

import java.util.List;

public interface SurveyService {

    public List<SurveyDTO> getList();

    public SurveyDTO getSurvey(String reference);

}
