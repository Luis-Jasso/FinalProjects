package com.mi.tienda.springbatchtienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mi.tienda.springbatchtienda.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
		
}
