package com.jlrm.springboot.app.item.models.service;

import java.util.List;

import com.jlrm.springboot.app.commons.models.entity.Producto;
import com.jlrm.springboot.app.item.models.Item;

public interface IItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	
	public Producto save(Producto producto);
	public Producto update(Producto producto, Long id);
	public void delete(Long id);
	
}
