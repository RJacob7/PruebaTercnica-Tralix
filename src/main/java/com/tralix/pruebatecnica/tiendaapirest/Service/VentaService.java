package com.tralix.pruebatecnica.tiendaapirest.Service;

import com.tralix.pruebatecnica.tiendaapirest.DTO.VentaDTO;
import com.tralix.pruebatecnica.tiendaapirest.Entities.Producto;
import com.tralix.pruebatecnica.tiendaapirest.Entities.Venta;
import com.tralix.pruebatecnica.tiendaapirest.Repository.ProductoRepository;
import com.tralix.pruebatecnica.tiendaapirest.Repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository){
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    //Registrar una nueva venta
    public Venta registrarVenta(VentaDTO ventaDTO){
        Long productoId = ventaDTO.getProductoId();

        //primero validamos que el producto exista
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto con ID " + ventaDTO.getProductoId() + " no encontrado"));

        //crear la venta con el producto
        Venta venta = new Venta();
        venta.setProducto(producto);
        venta.setCantidad(ventaDTO.getCantidad());
        venta.setFechaVenta(LocalDateTime.now());
        return ventaRepository.save(venta);
    }

    //Obtener todas la ventas
    public List<Venta> obtenerTodasLasVentas(){
        return ventaRepository.findAll();
    }

    //Obtener ventas por Id
    public Optional<Venta> obtenerVentaPorId(Long id){
        return ventaRepository.findById(id);
    }

    //Obtener ventas por producto
    public List<Venta> obtenerVentasPorProducto(Long productoId){
        if (!productoRepository.existById(productoId))
            throw new RuntimeException("Producto con ID " + productoId + " no encontrado");

        return ventaRepository.findByProductoId(productoId);
    }

    //Eliminar una venta
    public void eliminarVenta(Long ventaId){
        if (!ventaRepository.existsById(ventaId))
            throw new RuntimeException("Venta con ID " + ventaId + " no encontrada");

        ventaRepository.deleteById(ventaId);
    }
}
