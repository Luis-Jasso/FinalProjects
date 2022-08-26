package com.mi.tienda.springbatchtienda.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "descripcion")
	private String descipcion;
	@Column(name = "precio")
	private String precio;
	@Column(name = "stock")
	private int stock;
}
