package co.edu.uniquindio.p2.agentatelefonica.views.principal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlResponsive;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane {
	public PanelPrincipal(String name, Scene scene) {
		Header header = new Header(name);
		PanelInternoPrincipal centerPane = new PanelInternoPrincipal();

		header.setMinHeight(80);

		heightProperty().addListener(CtrlResponsive.generarResponsiveListenerHeaderStage(scene, header));

		setTop(header);
		setCenter(centerPane);
	}

}
