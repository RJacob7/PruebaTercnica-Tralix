package com.tralix.pruebatecnica.tiendaapirest.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoriaValidaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface CategoriaValida {
    String message() default "Categoria no valida. Debe ser: Electronica, Ropa, Alimentos, Hogar, Deportes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
