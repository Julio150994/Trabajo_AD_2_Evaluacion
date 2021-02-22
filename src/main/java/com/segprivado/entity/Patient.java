package com.segprivado.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="pacientes")
public class Patient {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	private int id;	
	
	
	@Column(name="nombre",nullable=false,length=30)
	private String nombre;
	
	
	@Column(name="apellidos",nullable=false,length=50)
	private String apellidos;
	
	
	@Column(name="edad",nullable=false)
	private int edad;
	
	
	@Column(name="direccion",nullable=false,length=100)
	private String direccion;
	
	
	@Column(name="foto",nullable=false,length=100)
	private String foto;
	
	
	@Column(name="username",updatable=true,length=30)
	private String username;
	
	
	@Column(name="password",updatable=true,length=30)
	private String password;
	
	
	@OneToOne(mappedBy="idPaciente",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Appointment cita;
	
	
	public Patient() {
		super();
	}
	
	public Patient(int id) {
		super();
		this.id = id;
	}
	
	public Patient(int id, String nombre, String apellidos, int edad, String direccion, String foto, String username,
			String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.direccion = direccion;
		this.foto = foto;
		this.username = username;
		this.password = password;
	}	

	public Patient(int id, String nombre, String apellidos, int edad, String direccion, String foto, String username,
			String password, Appointment cita) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.direccion = direccion;
		this.foto = foto;
		this.username = username;
		this.password = password;
		this.cita = cita;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public Appointment getCita() {
		return cita;
	}

	public void setCita(Appointment cita) {
		this.cita = cita;
	}
}
