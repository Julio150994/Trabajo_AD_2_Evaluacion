package com.segprivado.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="medicos")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre",nullable=false,length=30)
	private String nombre;
	
	@Column(name="apellidos",nullable=false,length=50)
	private String apellidos;
	
	@Column(name="edad",nullable=false)
	private int edad;
	
	@Column(name="fechaalta",nullable=false)
	private Date fechaalta;
	
	@Column(name="especialidad",nullable=false,length=40)
	private String especialidad;
	
	@Column(name="username",nullable=false,length=30)
	private String username;
	
	@Column(name="password",nullable=false,length=30)
	private String password;
	
	@OneToMany(mappedBy="idMedico",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Appointment> citas;
	
	
	public Doctor() {
		super();
	}

	public Doctor(int id, String nombre, String apellidos, int edad, Date fechaalta, String especialidad,
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
	
	/* Para meter un nº de citas determinado */
	public Doctor(int id, String nombre, String apellidos, int edad, Date fechaalta, String especialidad,
			String username, String password, List<Appointment> citas) {
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

	public List<Appointment> getCitas() {
		return citas;
	}

	public void setCitas(List<Appointment> citas) {
		this.citas = citas;
	}
}
