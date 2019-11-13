package com.jlrm.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jlrm.springboot.app.commons.models.entity.Producto;
import com.jlrm.springboot.app.item.models.Item;

@Service("itemServiceRestTemplate")
public class ItemService implements IItemService {

	@Autowired
	private RestTemplate restTemplate;

	String url = "http://microservicio-productos";

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	@Override
	public List<Item> findAll() {

		LOGGER.info("Find All**");

		List<Producto> productos = Arrays.asList(restTemplate.getForObject(url + "/listar", Producto[].class));

		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		Producto producto = restTemplate.getForObject(url + "/ver/{id}", Producto.class, pathVariables);

		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);

		ResponseEntity<Producto> response = restTemplate.exchange(url + "/crear", HttpMethod.POST, body,
				Producto.class);
		return response.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		HttpEntity<Producto> body = new HttpEntity<>(producto);
		ResponseEntity<Producto> response = restTemplate.exchange(url + "/editar/{id}", HttpMethod.PUT, body,
				Producto.class, pathVariables);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		restTemplate.delete(url+"/eliminar/{id}", pathVariables);
	}

}
