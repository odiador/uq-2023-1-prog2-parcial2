package co.edu.uniquindio.p2.agentatelefonica.util;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.CampoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Utility {

	public static void setAsNumberTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
					final ContextMenu menu = new ContextMenu();
					menu.getItems().add(new MenuItem("Este campo solo puede tener numeros"));
					menu.show(tf, Side.BOTTOM, 0, 0);

					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
						menu.hide();
					}));
					timeline.play();
				}
			}
		});
	}

	public static void setMaximumTextLength(TextField tf, int tamanio) {
		tf.setTextFormatter(new TextFormatter<String>(cambio -> {
			if (cambio.isContentChange()) {
				if (cambio.getControlNewText().length() > tamanio) {
					final ContextMenu menu = new ContextMenu();
					menu.getItems().add(new MenuItem("Este campo tiene maximo \n" + tamanio + " caracteres"));
					menu.show(cambio.getControl(), Side.BOTTOM, 0, 0);

					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
						menu.hide();
					}));
					timeline.play();
					return null;
				}
			}
			return cambio;
		}));
	}

	public static HBox generarHBox(String msg, Node... nodos) {
		Label label = new Label(msg);
		label.setId("label");
		HBox hbox = new HBox(20, label);
		hbox.getChildren().addAll(nodos);
		hbox.setId("centered-box");
		return hbox;
	}

	public static HBox generarHBox(int spacing, String msg, Node... nodos) {
		Label label = new Label(msg);
		label.setId("label");
		HBox hbox = new HBox(spacing, label);
		hbox.getChildren().addAll(nodos);
		hbox.setId("centered-box");
		return hbox;
	}

	public static void throwIfEmpty(String nombre, String msg) throws CampoException {
		if (nombre.isEmpty())
			throw new CampoException(msg);
	}

	public static void throwIfEmpty(String nombre) throws CampoException {
		throwIfEmpty(nombre, "Recuerda llenar todos los campos");
	}

	/**
	 * Intenta pasar una cadena a un entero, si no se puede se muestra un error
	 * 
	 * @param cadena
	 * @return
	 * @throws CampoException
	 */
	public static int pasarEnteroThrows(String cadena) throws CampoException {
		try {
			return Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			throw new CampoException("Recuerda solo colocar numeros en campos numericos");
		}
	}

	public static void throwifNull(Object object, String msg) throws ObjetoNoExisteException {
		if (object == null)
			throw new ObjetoNoExisteException(msg);
	}
}
