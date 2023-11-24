package com.anketa.service.validation;

public interface ValidationService {
    boolean validateTextField(String value);

    boolean validateReference(String value);
}
