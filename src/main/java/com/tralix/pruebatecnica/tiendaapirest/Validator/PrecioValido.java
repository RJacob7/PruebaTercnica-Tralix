package com.tralix.pruebatecnica.tiendaapirest.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PrecioValidoValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface PrecioValido {
    String message() default "El precio debe estar entre 0.01 y 1,000,0000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    //metodos para mi validacion
    double min() default 0.01;
    double max() default 10000000.0;
}
