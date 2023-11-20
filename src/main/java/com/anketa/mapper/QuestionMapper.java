package com.anketa.mapper;

import com.anketa.dto.QuestionDTO;
import com.anketa.model.Question;

public class QuestionMapper {
    public static QuestionDTO convertToDTO(Question question){
        return new QuestionDTO(
            question.getQuestion(),
            question.getAnswers().stream()
                .map(AnswerMapper::convertToDTO)
                .toList()
        );
    }
}
