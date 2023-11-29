package com.anketa.mapper;

import com.anketa.dto.AnswerDTO;
import com.anketa.dto.QuestionDTO;
import com.anketa.dto.UserDTO;
import com.anketa.model.Answer;
import com.anketa.model.Question;
import com.anketa.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class QuestionMapperTest {

    @InjectMocks
    QuestionMapper questionMapper;

    @Mock
    AnswerMapper answerMapper;

    @Test
    public void convertToDTOTest_ShouldMapQuestionToQuestionDTO(){

        String reference = "5fc88c59-d88a-4c10-b1a1-dbfd8efb0b67";
        String answerString = "Pizza";
        String userName = "Paco";
        String questionString = "What is your favourite food?";

        User user = User.builder()
                .name(userName)
                .build();

        UserDTO userDTO = new UserDTO(reference, userName);

        Answer answer = Answer.builder()
            .answer(answerString)
            .user(user)
            .build();

        AnswerDTO answerDTO = new AnswerDTO(reference, answerString, userDTO);

        Question question = Question.builder()
            .question(questionString)
            .answers(new ArrayList<>())
            .build();

        Mockito.when(answerMapper.convertToDTO(answer)).thenReturn(answerDTO);
        QuestionDTO questionDTO = questionMapper.convertToDTO(question);

        Assertions.assertNotNull(questionDTO);
        Assertions.assertEquals(questionString, questionDTO.question());
        Assertions.assertNotNull(questionDTO.answerList());
    }

}
