package com.anketa.service.validation;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidationServiceImpl implements ValidationService{
    @Override
    public boolean validateTextField(String value) {
        return (
            Objects.nonNull(value) &&
            !value.isBlank() &&
            value.matches("[a-zA-Z0-9 ]+")
        );
    }
}
