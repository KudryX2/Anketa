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

    private final String reference = "5fc88c59-d88a-4c10-b1a1-dbfd8efb0b67";
    private final String answer = "Pizza";
    private final String userName = "Paco";
    private final UserDTO userDTO = new UserDTO(reference, userName);


    @Test
    public void convertToDTOTest_ShouldMapAnswerToAnswerDTO(){

        User user = User.builder()
            .name(userName)
            .build();

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

    @Test
    public void convertToEntityTest_ShouldMapAnswerDTOToEntity(){

        AnswerDTO answerDTO = new AnswerDTO(reference, answer, userDTO);

        Answer answerEntity = answerMapper.convertToEntity(answerDTO);

        Assertions.assertNotNull(answer);
        Assertions.assertEquals(answer, answerEntity.getAnswer());
    }

}
