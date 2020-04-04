package com.ufv.practica2.agenda;

import java.util.ArrayList;

public class Agenda {
	
	private ArrayList<Contacto> contactos;
	
	
	public Agenda() {
		
	}


	public Agenda(ArrayList<Contacto> contactos) {
		super();
		this.contactos = contactos;
	}


	public ArrayList<Contacto> getContactos() {
		return contactos;
	}


	public void setContactos(ArrayList<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	
	
	
}
