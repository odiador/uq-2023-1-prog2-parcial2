package co.edu.uniquindio.p2.agentatelefonica.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class CtrlImages {

	public static Image generarUserImage() {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream("src/resources/NoImage.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Image(stream);
	}

}
