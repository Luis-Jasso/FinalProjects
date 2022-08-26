package com.rest.client.services;

import java.util.List;

import com.rest.client.entity.Producto;



public interface ProductoService {

	public List<Producto> getProductos();
	
	public Producto getProducto(int theId);
	
	public void saveProducto(Producto theProducto);
	
	public void deleteProducto(int theId);
	
}
