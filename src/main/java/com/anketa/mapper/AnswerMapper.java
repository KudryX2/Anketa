package com.anketa.mapper;

import com.anketa.dto.AnswerDTO;
import com.anketa.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    @Autowired
    UserMapper userMapper;

    public AnswerDTO convertToDTO(Answer answer){
        return new AnswerDTO(
            answer.getAnswer(),
            userMapper.convertToDTO(answer.getUser())
        );
    }
}
