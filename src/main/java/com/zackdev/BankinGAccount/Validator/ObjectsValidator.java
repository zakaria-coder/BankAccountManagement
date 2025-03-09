package com.zackdev.BankinGAccount.Validator;

import com.zackdev.BankinGAccount.Exception.ObjectValidationExeption;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator <T> {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator objectsValidator = validatorFactory.getValidator();

    public void validate(T objectToValidate) {
        Set<ConstraintViolation<T>> violations = objectsValidator.validate(objectToValidate);
        if (!violations.isEmpty()) {
            Set<String> errorMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationExeption(errorMessages,objectToValidate.getClass().getName());
        }
    }
}
