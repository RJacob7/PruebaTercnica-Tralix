package com.tralix.pruebatecnica.tiendaapirest.Entities;

import java.time.LocalDateTime;

public class Venta {

    private Long id ;
    private Long productoId;
    private int cantidad;
    private LocalDateTime fechaVenta;

    public Venta(){
    }

    public Venta(Long id, Long productoId, int cantidad, LocalDateTime fechaVenta) {
        this.id = id;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return this.productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
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
                ", productoId=" + productoId +
                ", cantidad=" + cantidad +
                ", fechaVenta=" + fechaVenta +
                '}';
    }
}
