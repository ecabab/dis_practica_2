package com.ufv.practica2;

import javax.servlet.annotation.WebServlet;

import com.ufv.practica2.agenda.Agenda;
import com.ufv.practica2.agenda.Contacto;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

  /**
  *
  */
  private static final long serialVersionUID = 1L;

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    Agenda agenda = new Agenda();
    agenda.readJSON();
    // agenda.contactos.add(new Contacto(1, "Emilio", "Caba", "UFV", "673847392",
    // "emilio@gmail.com", "Madrid"));

    // Cabecera
    final VerticalLayout verticalLayout = new VerticalLayout();
    verticalLayout.setWidth("100%");
    verticalLayout.setHeight("100%");

    // Tabla de contactos
    Grid<Contacto> contactosGrid = new Grid<>();
    contactosGrid.setItems(agenda.contactos);

    contactosGrid.addComponentColumn(contacto -> new Label(Integer.toString(contacto.getId()))).setCaption("##");

    contactosGrid.addComponentColumn(contacto -> new Label(contacto.getNombre())).setCaption("Nombre");

    contactosGrid.addComponentColumn(contacto -> new Label(contacto.getApellidos())).setCaption("Apellido");

    contactosGrid.addComponentColumn(contacto -> new Label(contacto.getEmpresa())).setCaption("Empresa");

    contactosGrid.addComponentColumn(contacto -> new Label(contacto.getTelefono())).setCaption("Teléfono");

    contactosGrid.addComponentColumn(contacto -> new Label(contacto.getEmail())).setCaption("Email");

    contactosGrid.addComponentColumn(contacto -> new Label(contacto.getDireccion())).setCaption("Dirección");

    contactosGrid.addComponentColumn(contacto -> {
      HorizontalLayout horLayout = new HorizontalLayout();

      Button editBtn = new Button("Editar");
      editBtn.addClickListener(click -> {
        System.out.print(contacto.getNombre());
      });
      Button delBtn = new Button("Borrar");
      horLayout.addComponents(editBtn, delBtn);

      return horLayout;
    });

    contactosGrid.setStyleName("contacts-grid");
    contactosGrid.setWidth("100%");
    contactosGrid.setHeight("100%");
    contactosGrid.setRowHeight(40);

    verticalLayout.addComponents(contactosGrid);

    setContent(verticalLayout);
  }

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
  @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
  }
}
