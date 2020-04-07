package com.ufv.practica2.agenda;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Agenda {
	
	public ArrayList<Contacto> contactos;
	
	public Agenda() {
		this.contactos = new ArrayList<Contacto>();
	}

	public Agenda(final ArrayList<Contacto> contactos) {
		super();
		this.contactos = contactos;
	}

	public ArrayList<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(final ArrayList<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public void readJSON() {
		final Gson gson = new Gson();

	    try (Reader reader = new FileReader("agenda.json")) {

	    	this.contactos = gson.fromJson(reader, new TypeToken<List<Contacto>>(){}.getType());

	     } catch (final IOException e) {
	    	 e.printStackTrace();
	     }     
	}

	public void generateJSON() {
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try (FileWriter writer = new FileWriter("agenda.json")) {
			
            gson.toJson(this.contactos, writer);
            
        } catch (final IOException e) {
            e.printStackTrace(); 
        }
	}

	public void createContacto(final Contacto contacto) {
		this.contactos.add(contacto);
	}

	public void deleteContacto(final Contacto contacto, final int index) {
		this.contactos.remove(index);
	}

	public void updateContacto(final Contacto contacto, final int index) {
		this.contactos.set(index, contacto);
	}
}
