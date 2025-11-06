package com.tralix.pruebatecnica.tiendaapirest.Repository;

import com.tralix.pruebatecnica.tiendaapirest.Entities.Venta;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VentaRepository {

    //Lista en memoria para almacenar pruebas
    private final List<Venta> ventas = new ArrayList<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    //CRUD
    //Guardar una nueva venta
    public Venta save(Venta venta){
        if (venta.getId() == null)
            venta.setId(idGenerator.getAndIncrement());

        //asignar a la fecha actual si no tiene dicha fecha de venta
        if (venta.getFechaVenta() == null)
            venta.setFechaVenta(LocalDateTime.now());

        ventas.add(venta);
        return venta;
    }

    //Obtener todas las ventas
    public List<Venta> findAll(){
        return new ArrayList<>(this.ventas);
    }

    //Buscar venta por Id
    public Optional<Venta> findById(Long id){
        return ventas.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    //Elimina una venta por Id
    public boolean deleteById(Long id){
        return ventas.removeIf(v -> v.getId().equals(id));
    }

    //Verifica si existe una venta con dicho Id
    public boolean existById(Long id){
        return ventas.stream().anyMatch(v -> v.getId().equals(id));
    }

    //Obtiene todas las ventas de un producto específico
    public List<Venta> findByProductoId(Long productoId){
        return ventas.stream().filter(v -> v.getProducto().getId().equals(productoId)).toList();
    }

    //Limpia todas la ventas
    public void deleteAll(){
        ventas.clear();
        idGenerator.set(1);
    }
}
