package co.edu.uniquindio.p2.agentatelefonica.views.scenes;

import co.edu.uniquindio.p2.agentatelefonica.views.principal.PanelPrincipal;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class EscenaPrincipal extends Scene {
	public EscenaPrincipal(String name) {
		super(new BorderPane(), 800, 600);
		setRoot(new PanelPrincipal(name, this));
	}
}
