package com.jlrm.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.jlrm.springboot.app.commons.models.entity.Producto;
import com.jlrm.springboot.app.item.clients.IProductoClienteRest;
import com.jlrm.springboot.app.item.models.Item;

@Service("feignService")
@Primary
public class ItemFeignService implements IItemService {
	@Autowired
	private IProductoClienteRest clientFeign;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemFeignService.class);
	
	@Override
	public List<Item> findAll() {
		LOGGER.info("Feign -> findAll");
		return clientFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		LOGGER.info("Feign -> findById");
		return new Item(clientFeign.getId(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return clientFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return clientFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clientFeign.eliminar(id);		
	}

}
