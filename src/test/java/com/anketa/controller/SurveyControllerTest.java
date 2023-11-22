package com.anketa.controller;

import com.anketa.dto.SurveyDTO;
import com.anketa.service.SurveyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.AbstractSet;
import java.util.ArrayList;

@SpringBootTest
public class SurveyControllerTest {

    @InjectMocks
    private SurveyController surveyController;

    @Mock
    private SurveyService surveyService;

    @Test
    public void getListTest_ShouldReturnAllTheSurveysList(){
        Mockito.when(surveyService.getList()).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(surveyController.getSurveyList());
    }

    @Test
    public void getSurveyTest_ShouldReturnSurveyByReference(){
        final String reference = "survey-ref";
        Mockito.when(surveyService.getSurvey(reference))
            .thenReturn(new SurveyDTO(reference, "surveyName", new ArrayList<>()));
        Assertions.assertNotNull(surveyController.getSurvey(reference));
    }
}
