package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlGrupo;
import co.edu.uniquindio.p2.agentatelefonica.model.Categoria;
import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelAnadirGrupo extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelAnadirGrupo(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		TextField tfNombre = new TextField();
		ComboBox<String> comboCategoria = new ComboBox<String>();

		comboCategoria.setItems(FXCollections.observableArrayList(Categoria.textValues()));
		VBox vbox = new VBox(20);
		HBox hbox = new HBox();
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonAnadir = new Boton("Añadir", e -> {
			CtrlGrupo.anadirGrupo(tfNombre.getText(), comboCategoria.getValue());
		});

		vbox.setId("centered-box");

		vbox.getChildren().add(Utility.generarHBox("Escribe el nombre del grupo", tfNombre));
		vbox.getChildren().add(Utility.generarHBox("Elige su categoria", comboCategoria));
		hbox.getChildren().addAll(botonAnadir, botonVolver);

		HBox.setHgrow(botonAnadir, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);

		setCenter(vbox);
		setBottom(hbox);
	}
}
