package com.segprivado.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="medicamentos")
public class Medicine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre",nullable=false,length=50)
	private String nombre;
	
	@Column(name="descripcion",nullable=false,length=100)
	private String descripcion;
	
	@Column(name="receta",nullable=false,length=1)
	private String receta;
	
	@Column(name="precio",nullable=false,length=1)
	private float precio;
	
	@Column(name="stock",nullable=false,length=1)
	private int stock;
	
	@ManyToMany
	@JoinTable(
		name="compra_medicamentos",
		joinColumns=@JoinColumn(name="idMedicamento"),
		inverseJoinColumns=@JoinColumn(name="idCompra")
	)
	private List<Purchase> compras;//para autogenerar la tabla compra_medicamentos
	
	
	public Medicine() {
		super();
	}

	public Medicine(int id, String nombre, String descripcion, String receta, float precio, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.receta = receta;
		this.precio = precio;
		this.stock = stock;
	}	

	public Medicine(int id, String nombre, String descripcion, String receta, float precio, int stock,
			List<Purchase> compras) {
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

	public List<Purchase> getCompras() {
		return compras;
	}

	public void setCompras(List<Purchase> compras) {
		this.compras = compras;
	}
}
