package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelFuncExtendida extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelFuncExtendida(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	public void initComponents() {

		VBox vbox = new VBox(20);
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonAgregarReunion = new Boton("Agregar Reunion", e -> {
			setCenter(new PanelAnadirReunion(eventoVolver));
			setBottom(null);
		});
		Boton botonEliminarAgenda = new Boton("Eliminar Reunion", e -> {
			setCenter(new PanelEliminarReunion(eventoVolver));
			setBottom(null);
		});
		Boton botonAgregarGrupo = new Boton("Agregar Grupo", e -> {
			setCenter(new PanelAnadirGrupo(eventoVolver));
			setBottom(null);
		});
		Boton botonEliminarGrupo = new Boton("Eliminar Grupo", e -> {
			setCenter(new PanelEliminarGrupo(eventoVolver));
			setBottom(null);
		});

		vbox.setId("centered-box");
		vbox.getChildren().addAll(botonAgregarReunion, botonEliminarAgenda, botonAgregarGrupo, botonEliminarGrupo);

		Insets insets = new Insets(0, 80, 0, 80);

		VBox.setMargin(botonAgregarReunion, insets);
		VBox.setMargin(botonEliminarAgenda, insets);
		VBox.setMargin(botonAgregarGrupo, insets);
		VBox.setMargin(botonEliminarGrupo, insets);

		setCenter(vbox);
		setBottom(botonVolver);
	}

}
