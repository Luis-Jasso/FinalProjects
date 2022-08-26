package com.rest.client.entity;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Data
public class Producto {

	private int id;
	private String nameProduct;
	private String descripcion;
	private int stock;

	
}
