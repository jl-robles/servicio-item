package com.jlrm.springboot.app.item.models;

import com.jlrm.springboot.app.commons.models.entity.Producto;

import lombok.Data;

@Data
public class Item {
	
	public Item() {
	}

	private Producto producto;
	private Integer cantidad;
	
	public Item(Producto producto, Integer cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

}
