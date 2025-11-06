package com.tralix.pruebatecnica.tiendaapirest.Service;

import com.tralix.pruebatecnica.tiendaapirest.DTO.ProductoDTO;
import com.tralix.pruebatecnica.tiendaapirest.Entities.Producto;
import com.tralix.pruebatecnica.tiendaapirest.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository){
        this.repository = repository;
    }

    //Aqui tenemos la logica de los Productos
    public Producto crearProducto(ProductoDTO productoDTO){
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setPrecio(productoDTO.getPrecio());
        return repository.save(producto);
    }

    //Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos(){
        return repository.findAll(); //metodos llamados del Repository de Producto
    }

    //Obtener producto por Id
    public Optional<Producto> obtenerProductoPorId(Long id){
        return repository.findById(id);
    }

    //Actualizar Producto
    public Producto actualizarProducto(Long id, ProductoDTO productoDTO){
        if (!repository.existById(id))
            throw new RuntimeException("Producto con ID " + id + " no encontrado");

        Producto productoActualizado = new Producto();
        productoActualizado.setNombre(productoDTO.getNombre());
        productoActualizado.setCategoria(productoDTO.getCategoria());
        productoActualizado.setPrecio(productoDTO.getPrecio());
        return repository.update(id, productoActualizado).orElseThrow(() -> new RuntimeException("Error al actualizar el producto"));
    }

    //Eliminar Producto
    public void eliminarProducto(Long id){
        if (!repository.existById(id))
            throw new RuntimeException("Producto con ID " + id + "no encontrado");

        repository.deleteById(id);
    }
}
