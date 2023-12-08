package com.anketa.mapper;

import com.anketa.dto.AnswerDTO;
import com.anketa.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnswerMapper {

    @Autowired
    UserMapper userMapper;

    public AnswerDTO convertToDTO(Answer answer){
        return new AnswerDTO(
            answer.getReference(),
            answer.getAnswer(),
            userMapper.convertToDTO(answer.getUser())
        );
    }

    public Answer convertToEntity(AnswerDTO answerDTO){
        return Answer.builder()
            .reference(String.valueOf(UUID.randomUUID()))
            .answer(answerDTO.answer())
            .build();
    }
}
