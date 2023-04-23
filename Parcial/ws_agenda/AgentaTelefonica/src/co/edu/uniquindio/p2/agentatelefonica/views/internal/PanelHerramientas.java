package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlAgenda;
import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelHerramientas extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelHerramientas(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		VBox vbox = new VBox(20);
		Boton btnListar = new Boton("Listar Contactos", e -> {
			setCenter(new PanelVerContactos());
			setBottom(new Boton("Volver", evtVolver2 -> {
				initComponents();
			}, "btn-volver"));
		});
		Boton btnHuecosLibres = new Boton("Indicar Huecos libres", e -> {
			CtrlAgenda.huecosLibres();
		});
		Boton btnListarReuniones = new Boton("Listar Reuniones", e -> {
			setCenter(new PanelVerReuniones());
			setBottom(new Boton("Volver", evtVolver2 -> {
				initComponents();
			}, "btn-volver"));
		});
		vbox.setId("centered-box");
		vbox.getChildren().add(btnHuecosLibres);
		vbox.getChildren().add(btnListar);
		vbox.getChildren().add(btnListarReuniones);

		Insets instets = new Insets(0, 80, 0, 80);

		VBox.setMargin(btnHuecosLibres, instets);
		VBox.setMargin(btnListar, instets);
		VBox.setMargin(btnListarReuniones, instets);

		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		setCenter(vbox);
		setBottom(botonVolver);
	}
}
