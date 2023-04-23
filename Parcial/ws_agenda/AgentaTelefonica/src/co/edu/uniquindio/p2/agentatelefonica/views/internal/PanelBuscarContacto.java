package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlContacto;
import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelBuscarContacto extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;
	private Boton botonBuscar;
	private TextField tfNombre;
	private TextField tfTelefono;

	public PanelBuscarContacto(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		VBox vbox = new VBox(20);
		tfNombre = new TextField();
		tfTelefono = new TextField();
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		botonBuscar = new Boton("Buscar", e -> {
			CtrlContacto.buscarContacto(tfNombre.getText(), tfTelefono.getText());
		});
		HBox botonesBox = new HBox(botonBuscar, botonVolver);

		Utility.setAsNumberTextfield(tfTelefono);
		Utility.setMaximumTextLength(tfTelefono, 11);

		vbox.setId("centered-box");
		vbox.getChildren().add(Utility.generarHBox("Escribe un nombre para buscar el contacto", tfNombre));
		vbox.getChildren().add(Utility.generarHBox("Escribe su numero de telefono", tfTelefono));

		HBox.setHgrow(botonBuscar, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);

		setCenter(vbox);
		setBottom(botonesBox);
	}

	public void setComportamientoBotonBuscar(EventHandler<? super MouseEvent> eventoBuscar) {
		botonBuscar.setEventoBtnPresionado(eventoBuscar);
	}

	public TextField getTfNombre() {
		return tfNombre;
	}

	public TextField getTfTelefono() {
		return tfTelefono;
	}

}
