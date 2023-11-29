package com.anketa.controller;

import com.anketa.dto.QuestionDTO;
import com.anketa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping(produces = {"application/json"})
    public String createQuestion(@RequestBody QuestionDTO questionDTO, @RequestParam String surveyReference){
        return questionService.createQuestion(questionDTO, surveyReference);
    }

}
