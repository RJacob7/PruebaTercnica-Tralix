package com.tralix.pruebatecnica.tiendaapirest.Service;

import com.tralix.pruebatecnica.tiendaapirest.Entities.Producto;
import com.tralix.pruebatecnica.tiendaapirest.Entities.Venta;
import com.tralix.pruebatecnica.tiendaapirest.Repository.VentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnaliticasServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private AnaliticasService analiticasService;

    //Creamos una lista de ventas (Arrange ventas de prueba)
    private List<Venta> ventasDePrueba;

    @BeforeEach
    public void setUp() {
        // Crear productos de prueba
        Producto laptop = new Producto(1L, "Laptop", "Electronica", new BigDecimal("1000.00"));
        Producto telefono = new Producto(2L, "Telefono", "Electronica", new BigDecimal("500.00"));
        Producto camisa = new Producto(3L, "Camisa", "Ropa", new BigDecimal("50.00"));
        Producto pantalon = new Producto(4L, "Pantalon", "Ropa", new BigDecimal("80.00"));
        Producto arroz = new Producto(5L, "Arroz", "Alimentos", new BigDecimal("20.00"));

        // Crear ventas de prueba
        Venta venta1 = new Venta(1L, laptop, 2, LocalDateTime.now());    // Laptop: 2 × 1000 = 2000
        Venta venta2 = new Venta(2L, laptop, 1, LocalDateTime.now());    // Laptop: 1 × 1000 = 1000
        Venta venta3 = new Venta(3L, telefono, 3, LocalDateTime.now());  // Telefono: 3 × 500 = 1500
        Venta venta4 = new Venta(4L, camisa, 4, LocalDateTime.now());    // Camisa: 4 × 50 = 200
        Venta venta5 = new Venta(5L, pantalon, 2, LocalDateTime.now());  // Pantalon: 2 × 80 = 160
        Venta venta6 = new Venta(6L, arroz, 5, LocalDateTime.now());     // Arroz: 5 × 20 = 100
        Venta venta7 = new Venta(7L, camisa, 2, LocalDateTime.now());    // Camisa: 2 × 50 = 100

        ventasDePrueba = Arrays.asList(venta1, venta2, venta3, venta4, venta5, venta6, venta7);
    }

    @Test
    public void testGetTotalVentas(){
        when(ventaRepository.findAll()).thenReturn(ventasDePrueba);

        //2. ACT
        BigDecimal totalVentas = analiticasService.getTotalVentas();

        //3. ASSERT
        BigDecimal totalEsperado = new BigDecimal("5060.00"); //dato que no deberia devolver segun la suma
        assertEquals(0, totalEsperado.compareTo(totalVentas));

        // Verificar que se llamo el repository
        verify(ventaRepository, times(1)).findAll();
    }
}
