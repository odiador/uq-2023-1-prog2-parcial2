package co.edu.uniquindio.p2.agentatelefonica.views.principal;

import co.edu.uniquindio.p2.agentatelefonica.util.Boton;
import co.edu.uniquindio.p2.agentatelefonica.views.internal.PanelAnadirContacto;
import co.edu.uniquindio.p2.agentatelefonica.views.internal.PanelBuscarContacto;
import co.edu.uniquindio.p2.agentatelefonica.views.internal.PanelEliminarContacto;
import co.edu.uniquindio.p2.agentatelefonica.views.internal.PanelFuncExtendida;
import co.edu.uniquindio.p2.agentatelefonica.views.internal.PanelHerramientas;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelInternoPrincipal extends BorderPane {
	public PanelInternoPrincipal() {
		initComponents();
	}

	public void initComponents() {
		VBox box = new VBox(20);
		EventHandler<? super MouseEvent> eventoVolver = evtVolver -> initComponents();

		Boton anadirContactos = new Boton("Añadir contacto", e -> {
			setCenter(new PanelAnadirContacto(eventoVolver));
		});
		Boton buscarContacto = new Boton("Buscar contacto", e -> {
			setCenter(new PanelBuscarContacto(eventoVolver));
		});
		Boton eliminarContacto = new Boton("Eliminar contacto", e -> {
			setCenter(new PanelEliminarContacto(eventoVolver));
		});

		Boton btnHerramientas = new Boton("Herramientas", e -> {
			setCenter(new PanelHerramientas(eventoVolver));
		});
		Boton btnFuncExtendida = new Boton("Funcionalidad Extendida", e -> {
			setCenter(new PanelFuncExtendida(eventoVolver));
		});

		box.getChildren().addAll(anadirContactos, buscarContacto, eliminarContacto, btnHerramientas, btnFuncExtendida);

		Insets insets = new Insets(0, 20, 0, 20);

		VBox.setMargin(anadirContactos, insets);
		VBox.setMargin(buscarContacto, insets);
		VBox.setMargin(eliminarContacto, insets);
		VBox.setMargin(btnHerramientas, insets);
		VBox.setMargin(btnFuncExtendida, insets);

		box.setId("centered-box");
		setCenter(box);

	}
}
