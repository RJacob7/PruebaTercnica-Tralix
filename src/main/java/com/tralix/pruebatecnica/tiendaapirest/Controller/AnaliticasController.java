package com.tralix.pruebatecnica.tiendaapirest.Controller;

import com.tralix.pruebatecnica.tiendaapirest.Service.AnaliticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Analytics")
public class AnaliticasController {

    private final AnaliticasService analiticasService;

    public AnaliticasController(AnaliticasService analiticasService) {
        this.analiticasService = analiticasService;
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, BigDecimal>> getTotalVentas() {
        BigDecimal total = analiticasService.getTotalVentas();
        return ResponseEntity.ok(Map.of("totalVentas", total));
    }

    @GetMapping("/top")
    public ResponseEntity<List<Map<String, Object>>> getTopProductos(
            @RequestParam(defaultValue = "3") int n) {
        List<Map<String, Object>> topProductos = analiticasService.getTopProductos(n);
        return ResponseEntity.ok(topProductos);
    }

    @GetMapping("/categorias")
    public ResponseEntity<Map<String, BigDecimal>> getVentasPorCategoria() {
        Map<String, BigDecimal> ventasPorCategoria = analiticasService.getVentasPorCategoria();
        return ResponseEntity.ok(ventasPorCategoria);
    }

    @GetMapping("/promedio")
    public ResponseEntity<Map<String, Object>> getPromedioVentaPorProducto() {
        Map<String, Object> promedios = analiticasService.getPromedioVentaPorProducto();
        return ResponseEntity.ok(promedios);
    }
}
