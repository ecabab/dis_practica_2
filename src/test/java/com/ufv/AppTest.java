package com.ufv;

import static org.junit.Assert.*;

import java.util.ArrayList;

import com.ufv.practica2.agenda.Agenda;
import com.ufv.practica2.agenda.Contacto;

import org.junit.Before;
import org.junit.Test;



public class AppTest {


	Agenda agenda = null;
	Contacto contacto1 = new Contacto();
	Contacto contacto2 = new Contacto();
	@Before
	public void initialize() {
		agenda = new Agenda();
		contacto1 = new Contacto(1,"Manuel","Garcia","Endesa","633435432","manolito@gmail.com","Eduardo javier 6");
	}
	@Test
	public void testProbarSeters() {	
		contacto2.setId(2);
		contacto2.setNombre("Javi");
		contacto2.setApellidos("Martin");
		contacto2.setEmpresa("Endesa");
		contacto2.setTelefono("6654636733");
		contacto2.setEmail("javier@hotmail.com");
		contacto2.setDireccion("Martin Javi 6");
	}
	@Test
	public void testGuardarContacto() {
		assertEquals("Manuel", contacto1.getNombre());
		assertEquals("Garcia", contacto1.getApellidos());
		assertEquals("Endesa", contacto1.getEmpresa());
		assertEquals("633435432", contacto1.getTelefono());
		assertEquals("manolito@gmail.com", contacto1.getEmail());
		assertEquals("Eduardo javier 6", contacto1.getDireccion());
	}
	@Test
	public void testGuardarAgenda() {		
		agenda.createContacto(contacto1);
		assertEquals("Manuel",agenda.contactos.get(0).getNombre());
	}
	
	@Test
	public void testActualizarContacto() {
		agenda.createContacto(contacto1);
		contacto1.setNombre("Javi");
		agenda.updateContacto(contacto1);
		assertEquals("Javi",agenda.contactos.get(0).getNombre());
	}
	
	@Test
	public void testEliminarContacto() {
		agenda.createContacto(contacto1);
		agenda.deleteContacto(contacto1);
		assertEquals(0,agenda.contactos.size());
	}
	
	@Test
	public void testGenerarJSON() {
		agenda.createContacto(contacto1);
		agenda.generateJSON();
		agenda.readJSON();
		assertEquals("Manuel",agenda.contactos.get(0).getNombre());
		agenda.createContacto(contacto2);
		agenda.generateJSON();
		agenda.readJSON();
		assertEquals(null,agenda.contactos.get(1).getNombre());
	}
	
	@Test
	public void testLeerJSON() {
		agenda.readJSON();
		assertEquals(0, agenda.contactos.size());
		agenda.createContacto(contacto1);
		Agenda agenda2 = new Agenda();
		agenda2.readJSON();
		assertEquals("Manuel",agenda2.contactos.get(0).getNombre());
	}
	
	@Test
	public void testAñadirContactos() {
		ArrayList<Contacto> contactos = null;
		agenda.setContactos(contactos);
		assertNull(agenda.getContactos());
	}
	
	@Test
	public void testContactoNoEsta() {
		assertEquals(-1,agenda.getIndexOfContact(contacto1));
	}
	
	
	
	
	
	
	
	
	



}
