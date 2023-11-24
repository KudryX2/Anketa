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
    public void validateTextFieldTest_ShouldReturnFalseIsNull(){
        Assertions.assertFalse(validationService.validateTextField(null));
    }

    @Test
    public void validateTextFieldTest_ShouldReturnFalseIsEmpty(){
        Assertions.assertFalse(validationService.validateTextField(" "));
    }

    @Test
    public void validateTextFieldTest_ShouldReturnFalseInvalidCharacters(){
        Assertions.assertFalse(validationService.validateTextField("') OR 1 = 1 drop tabl..."));
    }

    @Test
    public void validateReferenceTest_ShouldReturnTrue(){
        Assertions.assertTrue(validationService.validateReference("1f954bde-da5c-4348-8f85-23b62633e483"));
    }

    @Test
    public void validateReferenceTest_ShouldReturnFalseInvalidLength(){
        Assertions.assertFalse(validationService.validateReference("1f954bde-da5c-4348-8f85-23b"));
    }

    @Test
    public void validateReferenceTest_ShouldReturnFalseInvalidCharacters(){
        Assertions.assertFalse(validationService.validateReference("') OR 1 = 1 drop tabl..."));
    }
}
