package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import static co.edu.uniquindio.p2.agentatelefonica.util.Utility.generarHBox;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlReunion;
import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelAnadirReunion extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelAnadirReunion(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		TextField tfNombre = new TextField();
		TextField tfDescripcion = new TextField();
		DatePicker datePicker = new DatePicker();
		TextField tfHora = new TextField();
		TextField tfMinutos = new TextField();

		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonAnadir = new Boton("Añadir Reunion", e -> {
			CtrlReunion.anadirReunion(tfNombre.getText(), tfDescripcion.getText(), datePicker.getValue(),
					tfHora.getText(), tfMinutos.getText());
		});
		VBox vbox = new VBox(20);

		Utility.setAsNumberTextfield(tfHora);
		Utility.setAsNumberTextfield(tfMinutos);
		Utility.setMaximumTextLength(tfHora, 2);
		Utility.setMaximumTextLength(tfMinutos, 2);

		vbox.getChildren().add(generarHBox("Escribe el nombre de la reunion", tfNombre));
		vbox.getChildren().add(generarHBox("Escribe la descripcion de la reunion", tfDescripcion));
		vbox.getChildren().add(generarHBox("Escribe la fecha de la reunion", datePicker));
		vbox.getChildren().add(generarHBox(0, "Escribe la hora de la reunion", tfHora, new Label(","), tfMinutos));

		vbox.setId("centered-box");

		HBox hbox = new HBox();
		hbox.getChildren().addAll(botonAnadir, botonVolver);
		HBox.setHgrow(botonAnadir, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);
		setCenter(vbox);
		setBottom(hbox);
	}
}
