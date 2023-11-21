package com.anketa.mapper;

import com.anketa.dto.UserDTO;
import com.anketa.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO convertToDTO(User user){
        return new UserDTO(
            user.getName()
        );
    }
}
