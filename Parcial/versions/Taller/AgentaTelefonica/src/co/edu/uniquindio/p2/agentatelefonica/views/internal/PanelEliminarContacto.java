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

public class PanelEliminarContacto extends BorderPane {
	public PanelEliminarContacto(EventHandler<? super MouseEvent> eventoVolver) {
		VBox vbox = new VBox(20);
		TextField tfNombre = new TextField();
		TextField tfTelefono = new TextField();
		Boton botonEliminar = new Boton("Eliminar", e -> {
			CtrlContacto.eliminarContacto(tfNombre.getText(), tfTelefono.getText());
		});
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		HBox botonesBox = new HBox(botonEliminar, botonVolver);

		Utility.setAsNumberTextfield(tfTelefono);
		Utility.setMaximumTextLength(tfTelefono, 11);

		vbox.setId("centered-box");
		vbox.getChildren().add(Utility.generarHBox("Escribe el nombre del contacto", tfNombre));
		vbox.getChildren().add(Utility.generarHBox("Escribe el telefono del contacto", tfTelefono));

		HBox.setHgrow(botonEliminar, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);
		setCenter(vbox);
		setBottom(botonesBox);
	}
}
