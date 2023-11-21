package com.anketa.mapper;

import com.anketa.dto.QuestionDTO;
import com.anketa.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    public QuestionDTO convertToDTO(Question question){
        return new QuestionDTO(
            question.getQuestion(),
            question.getAnswers().stream()
                .map(answerMapper::convertToDTO)
                .toList()
        );
    }
}
