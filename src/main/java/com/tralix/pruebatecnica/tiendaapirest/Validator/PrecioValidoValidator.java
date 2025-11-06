package com.tralix.pruebatecnica.tiendaapirest.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PrecioValidoValidator implements ConstraintValidator<PrecioValido, BigDecimal> {

    private double min;
    private double max;

    @Override
    public void initialize(PrecioValido constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(BigDecimal precio, ConstraintValidatorContext constraintValidatorContext) {
        if (precio == null)
            return false;

        BigDecimal minBD = BigDecimal.valueOf(min);
        BigDecimal maxBD = BigDecimal.valueOf(max);

        return precio.compareTo(minBD) >= 0 && precio.compareTo(maxBD) <= 0;
    }
}
