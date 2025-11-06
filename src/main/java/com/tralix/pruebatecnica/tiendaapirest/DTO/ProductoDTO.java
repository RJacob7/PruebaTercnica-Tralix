package com.tralix.pruebatecnica.tiendaapirest.DTO;

import com.tralix.pruebatecnica.tiendaapirest.Validator.CategoriaValida;
import com.tralix.pruebatecnica.tiendaapirest.Validator.PrecioValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public class ProductoDTO {

    //tenemos que aplicar las validaciones
    @NotBlank(message = "El nombre es obligatorio") //validar que si incluya datos
    @Size(min = 3, max = 50, message = "el nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @CategoriaValida //implementacion de validacion personalizada
    private String categoria;

    @PrecioValido(min = 0.01, max = 1000000)
    private BigDecimal precio;

    //Constructor vacio como metodo sobrecargado para otros usos
    public ProductoDTO(){
    }

    public ProductoDTO(String nombre, String categoria, BigDecimal precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
