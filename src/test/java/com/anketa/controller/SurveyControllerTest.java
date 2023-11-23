package com.anketa.controller;

import com.anketa.dto.SurveyDTO;
import com.anketa.service.SurveyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class SurveyControllerTest {

    @InjectMocks
    private SurveyController surveyController;

    @Mock
    private SurveyService surveyService;

    private final String reference = "survey-ref";
    private final String surveyName = "survey name";
    private final SurveyDTO surveyDTO = new SurveyDTO(reference, surveyName, new ArrayList<>());


    @Test
    public void getListTest_ShouldReturnAllTheSurveysList(){
        Mockito.when(surveyService.getList()).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(surveyController.getSurveyList());
    }

    @Test
    public void getSurveyTest_ShouldReturnSurveyByReference(){
        Mockito.when(surveyService.getSurvey(reference)).thenReturn(surveyDTO);
        Assertions.assertNotNull(surveyController.getSurvey(reference));
    }

    @Test
    public void createSurveyTest_ShouldAddNewSurveyAndReturnReference(){
        Mockito.when(surveyService.createSurvey(surveyDTO)).thenReturn(reference);
        Assertions.assertEquals(reference, surveyController.createSurvey(surveyDTO));
    }
}
