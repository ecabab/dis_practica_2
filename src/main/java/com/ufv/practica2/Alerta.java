package com.ufv.practica2;

import com.ufv.practica2.agenda.Agenda;
import com.ufv.practica2.agenda.Contacto;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Alerta extends Window {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Alerta(String header, Agenda agenda, Contacto contacto) {
        super(header);

        VerticalLayout vLayout = new VerticalLayout();
        Label msg = new Label("¿Está seguro de que quiere borrar el contacto " + contacto.getNombre() + " " + contacto.getApellidos() + "?");

        HorizontalLayout hLayout = new HorizontalLayout();
        Button btnOp1 = new Button("Cancelar");
        btnOp1.addClickListener(click -> {
            close();
        });
        Button btnOp2 = new Button("Borrar");
        btnOp2.addClickListener(click -> {
            agenda.deleteContacto(contacto);
            close();
        });
        hLayout.addComponents(btnOp1, btnOp2);

        vLayout.addComponents(msg, hLayout);

        setContent(vLayout);

    }
}
