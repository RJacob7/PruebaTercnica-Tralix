package com.tralix.pruebatecnica.tiendaapirest.Service;

import com.tralix.pruebatecnica.tiendaapirest.Entities.Producto;
import com.tralix.pruebatecnica.tiendaapirest.Repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //Activamos mockito para el testeo
public class ProductoServiceTest {

    @Mock //creamos un repository de prueba falso
    public ProductoRepository repository;

    @InjectMocks // mandamos el repository de prueba a un ProductoService de prueba
    public ProductoService service;

    @Test
    public void testObtenerProductoPorIdCuandoExiste(){
        Long idBUscado = 1L;

        //1. ARRANGE
        //Creamos el producto de prueba
        Producto productoEsperado = new Producto(1L, "Laptop", "Electronica", new BigDecimal("30000.00"));

        //Decirle al repository mock: "cuando te pidan el ID 1, retorna este producto"
        when(repository.findById(idBUscado)).thenReturn(Optional.of(productoEsperado));

        //2. ACT
        // Llamar al método service.obtenerProductoPorId(1L)
        Optional<Producto> resultado = service.obtenerProductoPorId(idBUscado);

        //3. ASSERT
        // Verificar que el resultado no sea null, Verificar que el Optional contenga un producto, Verificar que sea el producto correcto
        assertTrue(resultado.isPresent()); //existe algo?
        assertEquals("Laptop", resultado.get().getNombre()); //es el producto correcto?
        assertEquals(idBUscado, resultado.get().getId()); //es el producto correcto?

        //verificar que efectivamente llamo al repository
        //verify(repository.findById(idBUscado));

    }
}
