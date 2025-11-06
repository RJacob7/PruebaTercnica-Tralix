package com.tralix.pruebatecnica.tiendaapirest.Controller;

import com.tralix.pruebatecnica.tiendaapirest.DTO.ProductoDTO;
import com.tralix.pruebatecnica.tiendaapirest.Entities.Producto;
import com.tralix.pruebatecnica.tiendaapirest.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService producto) {
        this.productoService = producto;
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoDTO productoDTO){
        Producto producto = productoService.crearProducto(productoDTO);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos(){
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }
}
