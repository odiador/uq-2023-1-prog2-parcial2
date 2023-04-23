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

public class PanelAnadirContacto extends BorderPane {
	public PanelAnadirContacto(EventHandler<? super MouseEvent> eventoVolver) {
		VBox vbox = new VBox(20);
		TextField tfNombre = new TextField();
		TextField tfAlias = new TextField();
		TextField tfDireccion = new TextField();
		TextField tfTelefono = new TextField();
		TextField tfEmail = new TextField();
		Boton botonAgregar = new Boton("Agregar", e -> {
			CtrlContacto.agregarContacto(tfNombre.getText(), tfAlias.getText(), tfDireccion.getText(),
					tfTelefono.getText(), tfEmail.getText());
		});
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		vbox.setId("centered-box");

		vbox.getChildren().add(Utility.generarHBox("Escribe un nombre", tfNombre));
		vbox.getChildren().add(Utility.generarHBox("Escribe un alias", tfAlias));
		vbox.getChildren().add(Utility.generarHBox("Escribe una direccion", tfDireccion));
		vbox.getChildren().add(Utility.generarHBox("Escribe un telefono", tfTelefono));
		vbox.getChildren().add(Utility.generarHBox("Escribe un email", tfEmail));

		Utility.setAsNumberTextfield(tfTelefono);
		Utility.setMaximumTextLength(tfTelefono, 11);

		HBox botonesBox = new HBox(botonAgregar, botonVolver);
		HBox.setHgrow(botonAgregar, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);
		setCenter(vbox);
		setBottom(botonesBox);
	}
}
