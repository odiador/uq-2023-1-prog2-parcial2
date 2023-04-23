package co.edu.uniquindio.p2.agentatelefonica.application;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlAgenda;
import co.edu.uniquindio.p2.agentatelefonica.views.scenes.EscenaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static String applicationCss;

	@Override
	public void start(Stage primaryStage) {
		try {
			applicationCss = getClass().getResource("application.css").toExternalForm();
			Scene scene = new EscenaPrincipal("Juan Manuel Amador Roa");
			CtrlAgenda.crearData(primaryStage);
			scene.getStylesheets().add(applicationCss);
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(400);
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
