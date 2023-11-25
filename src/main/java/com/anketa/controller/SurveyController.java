package com.anketa.controller;

import com.anketa.dto.SurveyDTO;
import com.anketa.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public String createSurvey(@RequestBody SurveyDTO surveyDTO){
        return surveyService.createSurvey(surveyDTO);
    }

    @DeleteMapping(value = "/{reference}")
    public ResponseEntity<String> deleteSurvey(@PathVariable String reference){
        surveyService.deleteSurvey(reference);
        return ResponseEntity.ok("Survey deleted");
    }


}
