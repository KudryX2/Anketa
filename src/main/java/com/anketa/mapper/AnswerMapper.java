package com.anketa.mapper;

import com.anketa.dto.AnswerDTO;
import com.anketa.model.Answer;

public class AnswerMapper {
    public static AnswerDTO convertToDTO(Answer answer){
        return new AnswerDTO(
            answer.getAnswer(),
            answer.getUser().getName()
        );
    }
}
