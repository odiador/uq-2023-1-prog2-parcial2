package co.edu.uniquindio.p2.agentatelefonica.views.principal;

import static co.edu.uniquindio.p2.agentatelefonica.util.Utility.generarHBox;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlAgenda;
import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelCrearAgenda extends BorderPane {
	public PanelCrearAgenda(Stage stage, Stage stageMain) {
		TextField tfNombre = new TextField();
		TextField tfCantContactos = new TextField();
		TextField tfCantGrupos = new TextField();
		TextField tfCantReuniones = new TextField();

		Utility.setAsNumberTextfield(tfCantReuniones);
		Utility.setAsNumberTextfield(tfCantContactos);
		Utility.setAsNumberTextfield(tfCantGrupos);

		Utility.setMaximumTextLength(tfCantReuniones, 9);
		Utility.setMaximumTextLength(tfCantContactos, 9);
		Utility.setMaximumTextLength(tfCantGrupos, 9);

		VBox vBox = new VBox(20);

		vBox.getChildren().add(generarHBox("Escribe el nombre de la agenda", tfNombre));
		vBox.getChildren().add(generarHBox("Escribe la cantidad de contactos", tfCantContactos));
		vBox.getChildren().add(generarHBox("Escribe la cantidad de grupos", tfCantGrupos));
		vBox.getChildren().add(generarHBox("Escribe la cantidad de reuniones", tfCantReuniones));

		vBox.setId("centered-box");
		setCenter(vBox);
		setBottom(new Boton("Aceptar", e -> CtrlAgenda.crearAgenda(stage, stageMain, tfNombre.getText(),
				tfCantContactos.getText(), tfCantGrupos.getText(), tfCantReuniones.getText())));
	}
}
