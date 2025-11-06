package com.tralix.pruebatecnica.tiendaapirest.Entities;

import java.time.LocalDateTime;

public class Venta {

    private Long id ;
    private Producto producto;
    private int cantidad;
    private LocalDateTime fechaVenta;

    public Venta(){
    }

    public Venta(Long id, Producto producto, int cantidad, LocalDateTime fechaVenta) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaVenta() {
        return this.fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public String toString() {
        return "La Venta es {" +
                "id=" + id +
                ", Producto=" + producto +
                ", cantidad=" + cantidad +
                ", fechaVenta=" + fechaVenta +
                '}';
    }
}
