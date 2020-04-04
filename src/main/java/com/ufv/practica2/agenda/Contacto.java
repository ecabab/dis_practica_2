package com.ufv.practica2.agenda;

public class Contacto {

	private String nombre;
	private String apellidos;
	private String empresa;
	private String telefono;
	private String email;
	private String dirrecion;
	
	
	public Contacto() {
		
	}
	
	
	public Contacto(String nombre, String apellidos, String empresa, String telefono, String email, String dirrecion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.empresa = empresa;
		this.telefono = telefono;
		this.email = email;
		this.dirrecion = dirrecion;
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
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDirrecion() {
		return dirrecion;
	}
	public void setDirrecion(String dirrecion) {
		this.dirrecion = dirrecion;
	}
	
	
	
	
	
}
