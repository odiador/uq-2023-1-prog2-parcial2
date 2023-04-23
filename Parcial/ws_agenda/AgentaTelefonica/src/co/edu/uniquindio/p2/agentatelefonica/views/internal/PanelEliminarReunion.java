package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlReunion;
import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelEliminarReunion extends BorderPane {

	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelEliminarReunion(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		TextField tfNombre = new TextField();
		VBox vbox = new VBox(20);
		HBox hbox = new HBox();
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonEliminar = new Boton("Eliminar", e -> {
			CtrlReunion.eliminarReunion(tfNombre.getText());
		});

		vbox.setId("centered-box");

		vbox.getChildren().add(Utility.generarHBox("Escribe el nombre de la reunion", tfNombre));
		hbox.getChildren().addAll(botonEliminar, botonVolver);

		HBox.setHgrow(botonEliminar, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);

		setCenter(vbox);
		setBottom(hbox);
	}

}
