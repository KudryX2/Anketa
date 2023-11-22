package com.anketa.controller;

import com.anketa.dto.SurveyDTO;
import com.anketa.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping(produces = {"application/json"})
    public List<SurveyDTO> getSurveyList(){
        return surveyService.getList();
    }

    @GetMapping(value = "/{reference}", produces = {"application/json"})
    public SurveyDTO getSurvey(@PathVariable String reference){
        return surveyService.getSurvey(reference);
    }

}
