package co.edu.uniquindio.p2.agentatelefonica.util;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Boton extends BorderPane {
	private Label label;

	public Boton(String text, EventHandler<? super MouseEvent> listener) {
		label = new Label(text);
		label.setId("btn");
		label.setOnMouseReleased(listener);

		setId("btn-case");
		setCenter(label);
	}

	public Boton(String text, EventHandler<? super MouseEvent> listener, String id) {
		this(text, listener);
		setId(id);
	}

	public void setEventoBtnPresionado(EventHandler<? super MouseEvent> listener) {
		label.setOnMouseReleased(listener);

	}
}
