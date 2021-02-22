package com.segprivado.model;

import java.util.List;

public class MedicineModel {
	private int id;
	private String nombre, descripcion,receta;
	private float precio;
	private int stock;
	private List<PurchaseModel> compras;
	
	
	public MedicineModel() {
		super();
	}	

	public MedicineModel(int id, String nombre, String descripcion, String receta, float precio, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.receta = receta;
		this.precio = precio;
		this.stock = stock;
	}

	public MedicineModel(int id, String nombre, String descripcion, String receta, float precio, int stock,
			List<PurchaseModel> compras) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.receta = receta;
		this.precio = precio;
		this.stock = stock;
		this.compras = compras;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getReceta() {
		return receta;
	}

	public void setReceta(String receta) {
		this.receta = receta;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<PurchaseModel> getCompras() {
		return compras;
	}

	public void setCompras(List<PurchaseModel> compras) {
		this.compras = compras;
	}
}
