package com.ufv.practica2.agenda;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	public Agenda ReadJSON() {
		 
		Gson gson = new Gson();
		 
		Agenda agenda = new Agenda();

	    try (Reader reader = new FileReader("agenda.json")) {

	    	agenda = gson.fromJson(reader, Agenda.class);
	        return agenda;

	     } catch (IOException e) {
	    	 
	    	 e.printStackTrace();
	         return agenda;
	           
	     }
	        
	}
	public void GenerateJSON(Agenda agenda) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try (FileWriter writer = new FileWriter("agenda.json")) {
			
            gson.toJson(agenda, writer);
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
        }
		
	}
	public void createContacto(Contacto contacto) {
		this.contactos.add(contacto);
	}
	public void deleteContacto(Contacto contacto, int index) {
		this.contactos.remove(index);
	}
	public void updateContacto(Contacto contacto, int index) {
		this.contactos.set(index, contacto);
	}
	
	
}
