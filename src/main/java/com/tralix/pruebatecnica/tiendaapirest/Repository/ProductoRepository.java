package com.tralix.pruebatecnica.tiendaapirest.Repository;

import com.tralix.pruebatecnica.tiendaapirest.Entities.Producto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductoRepository {

    //Lista en memoria
    private final List<Producto> productos = new ArrayList<>();

    //Generador automatico de ID's
    private final AtomicLong idGenerator = new AtomicLong(1);


    public ProductoRepository(){
        //Electronica
        save(new Producto(null, "Laptop AlienWare", "Electronica", new BigDecimal("30000.00")));
        save(new Producto(null, "iPhone 15 Pro", "Electronica", new BigDecimal("25000.00")));
        save(new Producto(null, "Samsung Galaxy S24", "Electronica", new BigDecimal("18000.00")));
        save(new Producto(null, "iPad Pro 12.9", "Electronica", new BigDecimal("22000.00")));
        save(new Producto(null, "AirPods Pro", "Electronica", new BigDecimal("5000.00")));

        //Ropa
        save(new Producto(null, "Chamarra Nike", "Ropa", new BigDecimal("2500.00")));
        save(new Producto(null, "Jeans Levi's 501", "Ropa", new BigDecimal("1800.00")));
        save(new Producto(null, "Sudadera Adidas", "Ropa", new BigDecimal("1200.00")));
        save(new Producto(null, "Playera Polo", "Ropa", new BigDecimal("800.00")));

        //Alimentos
        save(new Producto(null, "Arroz Blanco 5kg", "Alimentos", new BigDecimal("120.00")));
        save(new Producto(null, "Aceite de Oliva Extra", "Alimentos", new BigDecimal("250.00")));
        save(new Producto(null, "Pasta Italiana 500g", "Alimentos", new BigDecimal("45.00")));
        save(new Producto(null, "Café Orgánico 250g", "Alimentos", new BigDecimal("180.00")));
        save(new Producto(null, "Miel de Abeja 1L", "Alimentos", new BigDecimal("320.00")));

        //Hogar
        save(new Producto(null, "Licuadora Oster", "Hogar", new BigDecimal("1200.00")));
        save(new Producto(null, "Aspiradora Dyson", "Hogar", new BigDecimal("8000.00")));
        save(new Producto(null, "Microondas Samsung", "Hogar", new BigDecimal("2500.00")));

        //Deportes
        save(new Producto(null, "Balón FIFA Official", "Deportes", new BigDecimal("1500.00")));
        save(new Producto(null, "Raqueta Wilson Pro", "Deportes", new BigDecimal("3500.00")));
        save(new Producto(null, "Tenis Nike Air Max", "Deportes", new BigDecimal("3000.00")));
    }

    //CRUD DE REPOSITORY
    //metodo para guardar los productos
    public Producto save(Producto producto){
        if (producto.getId() == null)
            producto.setId(idGenerator.getAndIncrement());

        productos.add(producto);
        return producto;
    }

    //traer todos los productos
    public List<Producto> findAll(){
        return new ArrayList<>(this.productos);
    }

    //buscar por Id
    public Optional<Producto> findById(Long id){
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    //Actualizar un producto
    public Optional<Producto> update(Long id, Producto productoActualizado){
        Optional<Producto> productoExistente = findById(id);

        if (productoExistente.isPresent()){
            Producto producto = productoExistente.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setPrecio(productoActualizado.getPrecio());
            return Optional.of(producto);
        }
        return Optional.empty();
    }

    //Eliminar un producto
    public boolean deleteById(Long id){
        return productos.removeIf(p -> p.getId().equals(id));
    }

    //Verificar que existen un producto
    public boolean existById(Long id){
        return productos.stream().anyMatch(p -> p.getId().equals(id));
    }
}
