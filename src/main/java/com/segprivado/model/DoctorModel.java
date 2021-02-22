package com.segprivado.model;

import java.util.Date;
import java.util.List;


public class DoctorModel {
	private int id;
	private String nombre,apellidos;
	private int edad;
	private Date fechaalta;
	private String especialidad,username,password;
	private List<AppointmentModel> citas;
	
	
	public DoctorModel() {
		super();
	}
	
	public DoctorModel(int id, String nombre, String apellidos, int edad, Date fechaalta, String especialidad,
			String username, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.fechaalta = fechaalta;
		this.especialidad = especialidad;
		this.username = username;
		this.password = password;
	}

	public DoctorModel(int id, String nombre, String apellidos, int edad, Date fechaalta, String especialidad,
			String username, String password, List<AppointmentModel> citas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.fechaalta = fechaalta;
		this.especialidad = especialidad;
		this.username = username;
		this.password = password;
		this.citas = citas;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AppointmentModel> getCitas() {
		return citas;
	}

	public void setCitas(List<AppointmentModel> citas) {
		this.citas = citas;
	}
}
