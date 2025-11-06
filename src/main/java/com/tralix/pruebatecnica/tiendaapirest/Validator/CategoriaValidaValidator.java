package com.tralix.pruebatecnica.tiendaapirest.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class CategoriaValidaValidator implements ConstraintValidator<CategoriaValida, String> {

    //creo mi metodo unico para uso en esta clase, set es mas rapido que list
    private static final Set<String> CATEGORIAS_VALIDAS = Set.of(
      "ELECTRONICA",
      "ROPA",
      "ALIMENTOS",
      "HOGAR",
      "DEPORTES"
    );

    @Override
    public void initialize(CategoriaValida constraintAnnotation) {
    }

    @Override
    public boolean isValid(String categoria, ConstraintValidatorContext constraintValidatorContext) {
        if (categoria == null || categoria.trim().isEmpty())
            return false;

        return CATEGORIAS_VALIDAS.contains(categoria.toUpperCase().trim());
    }

}
