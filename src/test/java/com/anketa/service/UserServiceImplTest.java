package com.anketa.service;

import com.anketa.model.User;
import com.anketa.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void getUserTest_ShouldRetrieveUserByReference(){
        String reference = "userReference";

        Mockito.when(userRepository.findByReference(reference))
            .thenReturn(Optional.ofNullable(User.builder().reference(reference).build()));

        User user = userService.getUser(reference);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(reference, user.getReference());
    }
}
