package co.edu.uniquindio.p2.agentatelefonica.views.principal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlResponsive;
import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelPrincipal extends BorderPane {
	private String name;
	private Scene scene;

	public PanelPrincipal(String name, Scene scene) {
		this.name = name;
		this.scene = scene;
		initComponents();
	}

	private void initComponents() {
		Header header = new Header(name);

		VBox box = new VBox(20);
		EventHandler<? super MouseEvent> eventoVolver = evento -> setCenter(box);
		Boton botonTaller = new Boton("Acciones Taller", e -> setCenter(new PanelTaller(eventoVolver)));
		Insets insets = new Insets(0, 20, 0, 20);

		box.getChildren().add(botonTaller);
		heightProperty().addListener(CtrlResponsive.generarResponsiveListenerHeaderStage(scene, header));
		VBox.setMargin(botonTaller, insets);

		box.setId("centered-box");
		header.setMinHeight(80);

		setTop(header);
		setCenter(box);
	}

}
