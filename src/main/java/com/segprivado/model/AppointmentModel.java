package com.segprivado.model;

import java.util.Date;

public class AppointmentModel {
	private int id;
	private PatientModel idPaciente;
	private DoctorModel idMedico;
	private Date fecha;
	private String observaciones;
	
	public AppointmentModel() {
		super();
	}

	public AppointmentModel(int id, PatientModel idPaciente, DoctorModel idMedico, Date fecha, String observaciones) {
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

	public PatientModel getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(PatientModel idPaciente) {
		this.idPaciente = idPaciente;
	}

	public DoctorModel getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(DoctorModel idMedico) {
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
