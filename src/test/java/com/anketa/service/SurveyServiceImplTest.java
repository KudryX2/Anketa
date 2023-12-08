package com.anketa.service;

import com.anketa.dto.SurveyDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.SurveyMapper;
import com.anketa.model.Survey;
import com.anketa.model.User;
import com.anketa.repository.SurveyRepository;
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

    private final String reference = "1f954bde-da5c-4348-8f85-23b62633e483";
    private final String surveyName = "survey name";
    private final Survey survey = Survey.builder()
            .reference(reference)
            .name(surveyName)
            .build();

    private final SurveyDTO surveyDTO = new SurveyDTO(reference, surveyName, new ArrayList<>());


    @Test
    public void getListTest_ShouldReturnAllTheSurveysList(){
        Mockito.when(surveyRepository.findAll())
            .thenReturn(List.of(survey));
        Mockito.when(surveyMapper.convertToDTO(survey))
            .thenReturn(surveyDTO);

        Assertions.assertNotNull(surveyService.getList());
    }

    @Test
    public void getSurveyTest_ShouldRetrieveSurveyByReference(){
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.ofNullable(survey));

        Survey retrievedSurvey = surveyService.getSurvey(reference);
        Assertions.assertNotNull(retrievedSurvey);
        Assertions.assertEquals(reference, retrievedSurvey.getReference());
    }

    @Test
    public void getSurveyDTOTest_ShouldReturnSurveyDTOByReference(){
        Mockito.when(validationService.validateReference(reference))
            .thenReturn(true);
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.of(survey));
        Mockito.when(surveyMapper.convertToDTO(survey))
            .thenReturn(surveyDTO);

        SurveyDTO surveyResult = surveyService.getSurveyDTO(reference);

        Assertions.assertNotNull(surveyResult);
        Assertions.assertEquals(reference, surveyResult.reference());
    }

    @Test
    public void getSurveyDTOTest_ShouldThrowExceptionInvalidReference() {
        Mockito.when(validationService.validateReference(reference))
                .thenReturn(false);

        Assertions.assertThrows(
                BadRequestException.class, () -> surveyService.getSurveyDTO(reference));
    }

    @Test
    public void getSurveyDTOTest_ShouldThrowExceptionSurveyNotFound() {
        Mockito.when(validationService.validateReference(reference))
                .thenReturn(true);
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.empty());

        Assertions.assertThrows(
                BadRequestException.class, () -> surveyService.getSurveyDTO(reference));
    }

    @Test
    public void createSurveyTest_ShouldCreateNewSurvey(){
        Mockito.when(validationService.validateTextField(surveyName))
            .thenReturn(true);
        Mockito.when(surveyMapper.convertToEntity(surveyDTO))
            .thenReturn(survey);
        Mockito.when(surveyRepository.save(survey))
            .thenReturn(survey);

        Assertions.assertEquals(survey.getReference() , surveyService.createSurvey(surveyDTO));
    }

    @Test
    public void createSurveyTest_ShouldThrowBadExceptionWhenNameIsNotValid(){
        Mockito.when(validationService.validateTextField(surveyName))
            .thenReturn(false);

        Assertions.assertThrows(
            BadRequestException.class , () -> surveyService.createSurvey(surveyDTO));
    }

    @Test
    public void deleteSurveyTest_ShouldDeleteSurvey(){
        Mockito.when(validationService.validateReference(reference))
            .thenReturn(true);
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.of(survey));

        surveyService.deleteSurvey(reference);

        Mockito.reset(surveyRepository);

        Assertions.assertThrows(
            BadRequestException.class,
            () -> surveyService.getSurveyDTO(reference));
    }

    @Test
    public void deleteSurveyTest_ShouldThrowExceptionReferenceIsNotValid(){
        Mockito.when(validationService.validateReference(reference))
            .thenReturn(false);

        Assertions.assertThrows(
            BadRequestException.class,
            () -> surveyService.deleteSurvey(reference));
    }

}
