package com.segprivado.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="citas")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPaciente")
	private Patient idPaciente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idMedico")
	private Doctor idMedico;
	
	@Column(name="fecha",nullable=false)
	private Date fecha;
	
	@Column(name="observaciones",nullable=false,length=200)
	private String observaciones;	
	
	public Appointment() {
		super();
	}

	public Appointment(int id, Patient idPaciente, Doctor idMedico, Date fecha, String observaciones) {
		super();
		this.id = id;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.fecha = fecha;
		this.observaciones = observaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public Patient getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Patient idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Doctor getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Doctor idMedico) {
		this.idMedico = idMedico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
