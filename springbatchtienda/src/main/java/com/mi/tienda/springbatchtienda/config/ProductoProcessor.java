package com.mi.tienda.springbatchtienda.config;

import org.springframework.batch.item.ItemProcessor;

import com.mi.tienda.springbatchtienda.entity.Producto;

public class ProductoProcessor implements ItemProcessor<Producto, Producto> {

	@Override
	public Producto process(Producto item) throws Exception {
		if (item.getStock() > 10) { // Añadir los productos con mas de 10 en stock
			return item;
		}
		return null;
	}

}
