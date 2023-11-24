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

    @Override
    public boolean validateReference(String value) {
        return value.matches(
            "^[a-fA-F0-9]{8}" +
            "-[a-fA-F0-9]{4}" +
            "-[1-5][a-fA-F0-9]{3}" +
            "-[89aAbB][a-fA-F0-9]{3}" +
            "-[a-fA-F0-9]{12}$");
    }
}
