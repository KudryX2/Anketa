package com.anketa.service;

import com.anketa.dto.SurveyDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.SurveyMapper;
import com.anketa.model.Survey;
import com.anketa.repository.SurveyRepository;
import com.anketa.service.validation.ValidationService;
import com.anketa.service.validation.ValidationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SurveyServiceImplTest {

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyMapper surveyMapper;

    @Mock
    private ValidationServiceImpl validationService;

    private final String reference = "survey-ref";
    private final String name = "survey name";
    private final Survey survey = Survey.builder()
            .reference(reference)
            .name(name)
            .build();

    private final SurveyDTO surveyDTO = new SurveyDTO(reference, name, new ArrayList<>());


    @Test
    public void getListTest_ShouldReturnAllTheSurveysList(){
        Mockito.when(surveyRepository.findAll())
            .thenReturn(List.of(survey));
        Mockito.when(surveyMapper.convertToDTO(survey))
            .thenReturn(surveyDTO);

        Assertions.assertNotNull(surveyService.getList());
    }

    @Test
    public void getSurveyTest_ShouldReturnSurveyByReference(){
        Mockito.when(validationService.validateReference(reference))
            .thenReturn(true);
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.of(survey));
        Mockito.when(surveyMapper.convertToDTO(survey))
            .thenReturn(surveyDTO);

        SurveyDTO surveyResult = surveyService.getSurvey(reference);

        Assertions.assertNotNull(surveyResult);
        Assertions.assertEquals(reference, surveyResult.reference());
    }

    @Test
    public void getSurveyTest_ShouldThrowExceptionInvalidReference() {
        Mockito.when(validationService.validateReference(reference))
                .thenReturn(false);

        Assertions.assertThrows(
                BadRequestException.class, () -> surveyService.getSurvey(reference));
    }

    @Test
    public void getSurveyTest_ShouldThrowExceptionSurveyNotFound() {
        Mockito.when(validationService.validateReference(reference))
                .thenReturn(true);
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.empty());

        Assertions.assertThrows(
                BadRequestException.class, () -> surveyService.getSurvey(reference));
    }

    @Test
    public void createSurveyTest_ShouldCreateNewSurvey(){
        Mockito.when(validationService.validateTextField(surveyDTO.name()))
            .thenReturn(true);
        Mockito.when(surveyMapper.convertToEntity(surveyDTO))
            .thenReturn(survey);
        Mockito.when(surveyRepository.save(survey))
            .thenReturn(survey);

        Assertions.assertEquals(survey.getReference() , surveyService.createSurvey(surveyDTO));
    }

    @Test
    public void createSurveyTest_ShouldThrowBadExceptionWhenNameIsNotValid(){
        Mockito.when(validationService.validateTextField(surveyDTO.name()))
            .thenReturn(false);

        Assertions.assertThrows(
            BadRequestException.class , () -> surveyService.createSurvey(surveyDTO));
    }

}
