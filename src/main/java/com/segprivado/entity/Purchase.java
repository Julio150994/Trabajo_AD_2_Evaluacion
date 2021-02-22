package com.segprivado.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="compra")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="fecha",nullable=false)
	private Date fecha;
	
	@Column(name="precio",nullable=false)
	private float precio;
	
	@ManyToOne
	@JoinColumn(name="idPaciente")
	private Patient idPaciente;
	
	public Purchase() {
		super();
	}

	public Purchase(int id, Date fecha, float precio, Patient idPaciente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.precio = precio;
		this.idPaciente = idPaciente;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public Patient getIdPaciente() {
		return idPaciente;
	}


	public void setIdPaciente(Patient idPaciente) {
		this.idPaciente = idPaciente;
	}
}
