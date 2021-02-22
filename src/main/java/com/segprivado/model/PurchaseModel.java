package com.segprivado.model;

import java.util.Date;

public class PurchaseModel {
	private int id;
	private Date fecha;
	private float precio;
	private PatientModel idPaciente;	
	
	public PurchaseModel() {
		super();
	}

	public PurchaseModel(int id, Date fecha, float precio, PatientModel idPaciente) {
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

	public PatientModel getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(PatientModel idPaciente) {
		this.idPaciente = idPaciente;
	}
}
