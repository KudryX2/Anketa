package com.anketa.service;

import com.anketa.dto.SurveyDTO;
import com.anketa.exception.BadRequestException;
import com.anketa.mapper.SurveyMapper;
import com.anketa.model.Survey;
import com.anketa.repository.SurveyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.of(survey));
        Mockito.when(surveyMapper.convertToDTO(survey))
            .thenReturn(surveyDTO);

        SurveyDTO surveyResult = surveyService.getSurvey(reference);

        Assertions.assertNotNull(surveyResult);
        Assertions.assertEquals(reference, surveyResult.reference());
    }

    @Test
    public void getSurveyTest_ShouldThrowExceptionWhenSurveyNotFound() {
        Mockito.when(surveyRepository.findByReference(reference))
            .thenReturn(Optional.empty());

        Assertions.assertThrows(
                BadRequestException.class, () -> surveyService.getSurvey(reference));
    }

    @Test
    public void createSurveyTest_ShouldCreateNewSurvey(){
        Mockito.when(surveyMapper.convertToEntity(surveyDTO))
            .thenReturn(survey);
        Mockito.when(surveyRepository.save(survey))
            .thenReturn(survey);

        Assertions.assertEquals(survey.getReference() , surveyService.createSurvey(surveyDTO));
    }

    @Test
    public void createSurveyTest_ShouldThrowBadExceptionWhenNameIsNull(){
        Assertions.assertThrows(
            BadRequestException.class ,
            () -> surveyService.createSurvey(new SurveyDTO(null, null, null)));
    }

    @Test
    public void createSurveyTest_ShouldThrowBadExceptionWhenNameIsEmpty(){
        Assertions.assertThrows(
            BadRequestException.class ,
            () -> surveyService.createSurvey(new SurveyDTO(null, " ", null)));
    }

    @Test
    public void createSurveyTest_ShouldThrowBadExceptionWhenNameNotValid(){
        Assertions.assertThrows(
            BadRequestException.class ,
            () -> surveyService.createSurvey(new SurveyDTO(null, "') OR 1 = 1 drop tabl...", null)));
    }

}
