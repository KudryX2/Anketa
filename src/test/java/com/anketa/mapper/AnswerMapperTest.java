package com.anketa.mapper;

import com.anketa.dto.AnswerDTO;
import com.anketa.dto.UserDTO;
import com.anketa.model.Answer;
import com.anketa.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerMapperTest {

    @InjectMocks
    AnswerMapper answerMapper;

    @Mock
    UserMapper userMapper;

    @Test
    public void convertToDTOTest_ShouldMapAnswerToAnswerDTO(){

        String answer = "Pizza";
        String userName = "Paco";

        User user = User.builder()
            .name(userName)
            .build();

        UserDTO userDTO = new UserDTO(userName);

        Answer answerPizza = Answer.builder()
            .answer(answer)
            .user(user)
            .build();

        Mockito.when(userMapper.convertToDTO(user)).thenReturn(userDTO);
        AnswerDTO answerDTO = answerMapper.convertToDTO(answerPizza);

        Assertions.assertNotNull(answerDTO);
        Assertions.assertEquals(answer, answerDTO.answer());
        Assertions.assertEquals(userName, answerDTO.user().name());
    }

}
