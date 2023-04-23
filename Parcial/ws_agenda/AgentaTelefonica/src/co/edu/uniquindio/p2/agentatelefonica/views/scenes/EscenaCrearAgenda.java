package co.edu.uniquindio.p2.agentatelefonica.views.scenes;

import co.edu.uniquindio.p2.agentatelefonica.views.principal.PanelCrearAgenda;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaCrearAgenda extends Scene {

	public EscenaCrearAgenda(Stage stage, Stage stageMain) {
		super(new PanelCrearAgenda(stage, stageMain), 800, 600);
	}

}
