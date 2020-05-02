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

	public Agenda(ArrayList<Contacto> contactos) {
		super();
		this.contactos = contactos;
	}

	public ArrayList<Contacto> getContactos() {
		return this.contactos;
	}

	public void setContactos(ArrayList<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public void readJSON() {
		final Gson gson = new Gson();

	    try (Reader reader = new FileReader("agenda.json")) {

	    	this.contactos = gson.fromJson(reader, new TypeToken<List<Contacto>>(){}.getType());

	     } catch (IOException e) {
	    	 e.printStackTrace();
	     }     
	}

	public void generateJSON() {
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try (FileWriter writer = new FileWriter("agenda.json")) {
			
            gson.toJson(this.contactos, writer);
            
        } catch (IOException e) {
            e.printStackTrace(); 
        }
	}

	public void createContacto(Contacto contacto) {
		this.contactos.add(contacto);
		this.generateJSON();
	}

	public void updateContacto(Contacto contacto) {
		int index = this.getIndexOfContact(contacto);
		this.contactos.set(index, contacto);
		this.generateJSON();
	}

	public void deleteContacto(Contacto contacto) {
		int index = this.getIndexOfContact(contacto);
		this.contactos.remove(index);
		this.generateJSON();
	}

	public int getIndexOfContact(Contacto contacto) {
		for(int i = 0; i < this.contactos.size(); i++) {
			if(this.contactos.get(i).getId() == contacto.getId()) {
				return i;
			}
		}
		return -1;
	}

	public int getNextIndex() {
		if(this.contactos.size() == 0) {
			return 1;
		}
		else {
			return this.contactos.get(this.contactos.size() - 1).getId() + 1;
		}
	}
}
