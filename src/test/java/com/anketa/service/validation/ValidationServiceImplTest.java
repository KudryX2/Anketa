package com.anketa.service.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidationServiceImplTest {

    @InjectMocks
    private ValidationServiceImpl validationService;

    @Test
    public void validateTextFieldTest_ShouldReturnTrue(){
        Assertions.assertTrue(validationService.validateTextField("new anketa 1"));
    }

    @Test
    public void validateTextFieldTest_ShouldReturnFalseAsItIsNull(){
        Assertions.assertFalse(validationService.validateTextField(null));
    }

    @Test
    public void validateTextFieldTest_ShouldReturnFalseAsItIsEmpty(){
        Assertions.assertFalse(validationService.validateTextField(" "));
    }

    @Test
    public void validateTextFieldTest_ShouldReturnFalseAsItContainsInvalidCharacters(){
        Assertions.assertFalse(validationService.validateTextField("') OR 1 = 1 drop tabl..."));
    }

}
