package com.anketa.mapper;

import com.anketa.dto.UserDTO;
import com.anketa.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Test
    public void convertToDTOTest_ShouldMapUserToUserDTO(){

        String name = "Alberto";

        User user = User.builder()
            .name(name)
            .build();

        UserDTO userDTO = userMapper.convertToDTO(user);

        Assertions.assertNotNull(userDTO);
        Assertions.assertEquals(name, userDTO.name());
    }

}
