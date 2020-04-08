package com.ufv.practica2;

import com.ufv.practica2.agenda.Agenda;
import com.ufv.practica2.agenda.Contacto;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class Formulario extends Window {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Formulario(String header, Agenda agenda, int lastIndex) {
        super(header + Integer.toString(lastIndex));

        GridLayout form = new GridLayout();
        form.setColumns(2);
        form.setRows(4);

        final TextField nombreTextField = new TextField();
        nombreTextField.setCaption("Nombre");
        form.addComponent(nombreTextField, 0, 0);

        final TextField apellidosTextField = new TextField();
        apellidosTextField.setCaption("Apellidos");
        form.addComponent(apellidosTextField, 1, 0);

        final TextField empresaTextField = new TextField();
        empresaTextField.setCaption("Empresa");
        form.addComponent(empresaTextField, 0, 1);

        final TextField telefonoTextField = new TextField();
        telefonoTextField.setCaption("Teléfono");
        form.addComponent(telefonoTextField, 1, 1);

        final TextField emailTextField = new TextField();
        emailTextField.setCaption("Email");
        form.addComponent(emailTextField, 0, 2);

        final TextField direccionTextField = new TextField();
        direccionTextField.setCaption("Dirección");
        form.addComponent(direccionTextField, 1, 2);

        final Button saveBtn = new Button("Guardar");
        saveBtn.addClickListener(click -> {
            if(this.checkFields(nombreTextField.getValue(), telefonoTextField.getValue())) {
                Contacto newContacto = new Contacto(lastIndex, nombreTextField.getValue(), apellidosTextField.getValue(),
                                        empresaTextField.getValue(), telefonoTextField.getValue(), emailTextField.getValue(),
                                        direccionTextField.getValue());
                agenda.createContacto(newContacto);
            }
            close();
        });
        form.addComponent(saveBtn, 0, 3);

        final Button cancelBtn = new Button("Cancelar");
        cancelBtn.addClickListener(click -> close());
        form.addComponent(cancelBtn, 1, 3);

        // Open it in the UI
        setContent(form);
    }

    public Formulario(String header, Agenda agenda, Contacto contacto) {
        super(header + Integer.toString(contacto.getId()));

        GridLayout form = new GridLayout();
        form.setColumns(2);
        form.setRows(4);

        final TextField nombreTextField = new TextField();
        nombreTextField.setCaption("Nombre");
        nombreTextField.setValue(contacto.getNombre());
        form.addComponent(nombreTextField, 0, 0);

        final TextField apellidosTextField = new TextField();
        apellidosTextField.setCaption("Apellidos");
        apellidosTextField.setValue(contacto.getApellidos());
        form.addComponent(apellidosTextField, 1, 0);

        final TextField empresaTextField = new TextField();
        empresaTextField.setCaption("Empresa");
        empresaTextField.setValue(contacto.getEmpresa());
        form.addComponent(empresaTextField, 0, 1);

        final TextField telefonoTextField = new TextField();
        telefonoTextField.setCaption("Teléfono");
        telefonoTextField.setValue(contacto.getTelefono());
        form.addComponent(telefonoTextField, 1, 1);

        final TextField emailTextField = new TextField();
        emailTextField.setCaption("Email");
        emailTextField.setValue(contacto.getEmail());
        form.addComponent(emailTextField, 0, 2);

        final TextField direccionTextField = new TextField();
        direccionTextField.setCaption("Dirección");
        direccionTextField.setValue(contacto.getDireccion());
        form.addComponent(direccionTextField, 1, 2);

        final Button saveBtn = new Button("Guardar");
        saveBtn.addClickListener(click -> {
            if(this.checkFields(nombreTextField.getValue(), telefonoTextField.getValue())) {
                Contacto newContacto = new Contacto(contacto.getId(), nombreTextField.getValue(), apellidosTextField.getValue(),
                                        empresaTextField.getValue(), telefonoTextField.getValue(), emailTextField.getValue(),
                                        direccionTextField.getValue());
                agenda.updateContacto(newContacto);
            }
            close();
        });
        form.addComponent(saveBtn, 0, 3);

        final Button cancelBtn = new Button("Cancelar");
        cancelBtn.addClickListener(click -> close());
        form.addComponent(cancelBtn, 1, 3);

        // Open it in the UI
        setContent(form);
    }

    private boolean checkFields(String nombre, String telefono) {
        System.out.println(nombre);
        System.out.println(telefono);
        if(!nombre.isEmpty() && !telefono.isEmpty()) {
            return true;
        }
        return false;
    }

}
