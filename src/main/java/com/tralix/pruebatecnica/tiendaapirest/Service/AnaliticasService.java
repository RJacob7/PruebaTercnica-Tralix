package com.tralix.pruebatecnica.tiendaapirest.Service;

import com.tralix.pruebatecnica.tiendaapirest.Entities.Venta;
import com.tralix.pruebatecnica.tiendaapirest.Repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnaliticasService {

    private final VentaRepository repository;

    public AnaliticasService(VentaRepository repository) {
        this.repository = repository;
    }

    //1. CALCULAR EL TOTAL DE LAS VENTAS $$
    public BigDecimal getTotalVentas(){
        return repository.findAll().stream()
                .map(v -> v.getProducto().getPrecio()
                        .multiply(BigDecimal.valueOf(v.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //2. OBTENER LOS TOP N PRODUCTOS CON MAYOR INGRESO
    public List<Map<String, Object>> getTopProductos(int n){

        //primero agrupamos las ventas por productos y de ahi sumamos sus ingresos de cada producto
        Map<Long, BigDecimal> ingresosPorProducto = repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        venta -> venta.getProducto().getId(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                venta -> venta.getProducto().getPrecio()
                                        .multiply(BigDecimal.valueOf(venta.getCantidad())),
                                BigDecimal::add
                        )
                ));

        //Ordenar por ingresos descendente y tomar los top N
        return ingresosPorProducto.entrySet().stream()
                .sorted(Map.Entry.<Long, BigDecimal>comparingByValue().reversed())
                .limit(n)
                .map(entry -> {
                    // Buscar información del producto
                    Venta venta = repository.findAll().stream()
                            .filter(v -> v.getProducto().getId().equals(entry.getKey()))
                            .findFirst()
                            .orElse(null);

                    Map<String, Object> resultado = new HashMap<>();
                    if (venta != null) {
                        resultado.put("productoId", entry.getKey());
                        resultado.put("nombreProducto", venta.getProducto().getNombre());
                        resultado.put("categoria", venta.getProducto().getCategoria());
                        resultado.put("ingresoTotal", entry.getValue());
                    }
                    return resultado;
                })
                .collect(Collectors.toList());
    }

    //3. AGRUPAR VENTAS POR CATEGORIA Y CALCULAR EL TOTAL VENDIDO POR CADA UNA
    public Map<String, BigDecimal> getVentasPorCategoria(){
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(v -> v.getProducto().getCategoria(), Collectors.reducing(BigDecimal.ZERO,
                                v -> v.getProducto().getPrecio()
                                        .multiply(BigDecimal.valueOf(v.getCantidad())),
                                BigDecimal::add
                        )
                ));
    }

    //4. CALCULA EL MONTO PROMEDIO VENDIDO POR PRODUCTO
    public Map<String, Object> getPromedioVentaPorProducto(){

        //Agrupar ventas por producto
        Map<Long, List<Venta>> ventasPorProductos = repository.findAll().stream()
                .collect(Collectors.groupingBy(v -> v.getProducto().getId()));

        //calcular promedio para cada producto
        List<Map<String, Object>> promedios = ventasPorProductos.entrySet().stream()
                .map(entry -> {
                    Long productoId = entry.getKey();
                    List<Venta> ventas = entry.getValue();

                    //calcular total de ingresos del producto
                    BigDecimal totalIngresos = ventas.stream()
                            .map(venta -> venta.getProducto().getPrecio()
                                    .multiply(BigDecimal.valueOf(venta.getCantidad())))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    //calcular promedio
                    BigDecimal promedio = totalIngresos.divide(
                            BigDecimal.valueOf(ventas.size()),
                            2,
                            BigDecimal.ROUND_HALF_UP
                    );

                    Map<String, Object> resultado = new HashMap<>();
                    resultado.put("productoId", productoId);
                    resultado.put("nombreProducto", ventas.get(0).getProducto().getNombre());
                    resultado.put("numeroVentas", ventas.size());
                    resultado.put("totalIngresos", totalIngresos);
                    resultado.put("promedioVenta", promedio);

                    return resultado;
                })
                .collect(Collectors.toList());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("promedios", promedios);
        return respuesta;
    }

}
