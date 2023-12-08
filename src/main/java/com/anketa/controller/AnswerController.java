package com.anketa.controller;

import com.anketa.dto.AnswerDTO;
import com.anketa.service.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerServiceImpl answerService;

    @PostMapping(produces = {"application/json"})
    public String createAnswer(@RequestBody AnswerDTO answerDTO, @RequestParam String questionReference){
        return answerService.createAnswer(answerDTO, questionReference);
    }

}
