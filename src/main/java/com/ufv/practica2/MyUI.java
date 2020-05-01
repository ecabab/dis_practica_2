package com.ufv.practica2;

import javax.servlet.annotation.WebServlet;

import com.ufv.practica2.agenda.Agenda;
import com.ufv.practica2.agenda.Contacto;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

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

    // Grid
    final VerticalLayout verticalLayout = new VerticalLayout();
    verticalLayout.setWidth("100%");


    Grid<Contacto> contactosGrid = new Grid<>();

    // Cabecera
    final HorizontalLayout hlHeader = new HorizontalLayout();
    Label titulo = new Label("Agenda de contactos");
    Button createBtn = new Button("Crear Contacto");
    createBtn.addClickListener(click -> {
      int windowWidth = getPage().getBrowserWindowWidth();
      int windowHeight = getPage().getBrowserWindowHeight();

      Formulario form = new Formulario("Creando contacto - ID:", agenda,
          agenda.contactos.get(agenda.contactos.size() - 1).getId() + 1);
      form.setHeight("300px");
      form.setWidth("400px");
      form.setPosition(windowWidth / 2 - 200, windowHeight / 2 - 150);
      form.setModal(true);

      form.addCloseListener(new CloseListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void windowClose(CloseEvent e) {
          contactosGrid.setItems(agenda.contactos);
        }
      });

      UI.getCurrent().addWindow(form);
    });

    hlHeader.addComponents(titulo, createBtn);

    // Header styles
    hlHeader.setStyleName("header");
    createBtn.setStyleName("create-btn");

    // Tabla de contactos
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
        int windowWidth = getPage().getBrowserWindowWidth();
        int windowHeight = getPage().getBrowserWindowHeight();

        Formulario form = new Formulario("Editando contacto - ID:", agenda, contacto);
        form.setHeight("300px");
        form.setWidth("400px");
        form.setPosition(windowWidth / 2 - 200, windowHeight / 2 - 150);
        form.setModal(true);

        form.addCloseListener(new CloseListener() {
          private static final long serialVersionUID = 1L;

          @Override
          public void windowClose(CloseEvent e) {
            contactosGrid.setItems(agenda.contactos);
          }
        });

        UI.getCurrent().addWindow(form);
      });

      Button delBtn = new Button("Borrar");
      delBtn.addClickListener(click -> {
        int windowWidth = getPage().getBrowserWindowWidth();
        int windowHeight = getPage().getBrowserWindowHeight();

        Alerta alerta = new Alerta("Borrar Contacto", agenda, contacto);
        alerta.setHeight("150px");
        alerta.setWidth("450px");
        alerta.setPosition(windowWidth / 2 - 225, windowHeight / 2 - 75);
        alerta.setModal(true);

        alerta.addCloseListener(new CloseListener() {
          private static final long serialVersionUID = 1L;

          @Override
          public void windowClose(CloseEvent e) {
            contactosGrid.setItems(agenda.contactos);
          }
        });

        UI.getCurrent().addWindow(alerta);
      });

      horLayout.addComponents(editBtn, delBtn);

      return horLayout;
    });

    contactosGrid.setStyleName("contacts-grid");
    contactosGrid.setWidth("100%");
    contactosGrid.setHeight("100%");
    contactosGrid.setRowHeight(40);

    verticalLayout.addComponents(hlHeader, contactosGrid);

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
