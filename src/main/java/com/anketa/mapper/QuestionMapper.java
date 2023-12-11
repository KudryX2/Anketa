package com.anketa.mapper;

import com.anketa.dto.QuestionDTO;
import com.anketa.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    public QuestionDTO convertToDTO(Question question){
        return new QuestionDTO(
            question.getReference(),
            question.getQuestion(),
            question.getAnswers().stream()
                .map(answerMapper::convertToDTO)
                .toList()
        );
    }

    public Question convertToEntity(QuestionDTO questionDTO){
        return Question.builder()
                .reference(String.valueOf(UUID.randomUUID()))
                .question(questionDTO.question())
                .build();
    }
}
