package com.tralix.pruebatecnica.tiendaapirest.DTO;

import jakarta.validation.constraints.*;

public class VentaDTO {

    @NotNull(message = "El producto Id es obligatorio")
    @Min(value = 1, message = "El id del producto debe ser mayor a 0")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Max(value = 1000, message = "La cantidad no puede exceder 1000 unidades")
    private int cantidad;

    public VentaDTO(){

    }

    public VentaDTO(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
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
}
