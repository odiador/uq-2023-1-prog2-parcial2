package co.edu.uniquindio.p2.agentatelefonica.controllers;

import co.edu.uniquindio.p2.agentatelefonica.views.principal.Header;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class CtrlResponsive {
	/**
	 * Cambia el tamaño del header cada vez que se actualiza el alto de la ventana,
	 * se usa para hacer la ventana responsiva
	 * 
	 * @param scene
	 * @param header
	 * @return
	 */
	public static ChangeListener<? super Number> generarResponsiveListenerHeaderStage(Scene scene, Header header) {
		return (observable, oldValue, newValue) -> {
			// obtiene la altura de la escena
			double height = scene.getHeight();
			double headerHeight = height * 0.2;
			if (headerHeight < 80)
				headerHeight = 80;
			header.setPrefHeight(headerHeight);
		};
	}

	/**
	 * Escala la imagen del header para hacerla responsive
	 * 
	 * @param header
	 * @param imgView
	 */
	public static void escalarImagenHeaderResponsive(Header header, ImageView imgView) {
		DoubleBinding multiply = header.heightProperty().multiply(0.85).subtract(67.9d);

		imgView.fitHeightProperty().bind(multiply);
		imgView.fitWidthProperty().bind(multiply);
	}

}
