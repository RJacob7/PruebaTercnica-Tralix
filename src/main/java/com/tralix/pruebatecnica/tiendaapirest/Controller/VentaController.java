package com.tralix.pruebatecnica.tiendaapirest.Controller;

import com.tralix.pruebatecnica.tiendaapirest.DTO.VentaDTO;
import com.tralix.pruebatecnica.tiendaapirest.Entities.Venta;
import com.tralix.pruebatecnica.tiendaapirest.Service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@Valid @RequestBody VentaDTO ventaDTO){
        Venta venta = ventaService.registrarVenta(ventaDTO);
        return new ResponseEntity<>(venta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas(){
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }
}
