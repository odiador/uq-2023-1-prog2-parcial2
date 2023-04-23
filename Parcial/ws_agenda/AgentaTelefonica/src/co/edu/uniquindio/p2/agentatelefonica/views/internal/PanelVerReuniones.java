package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlTablaReuniones;
import co.edu.uniquindio.p2.agentatelefonica.model.Reunion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PanelVerReuniones extends BorderPane {
	public PanelVerReuniones() {
		initComponents();
	}

	private void initComponents() {
		TableView<Reunion> tablaContactos = new TableView<Reunion>();
		TableColumn<Reunion, String> colNombre = new TableColumn<Reunion, String>("Nombre");
		TableColumn<Reunion, String> colDescripcion = new TableColumn<Reunion, String>("Descripcion");
		TableColumn<Reunion, String> colFechaHora = new TableColumn<Reunion, String>("Fecha y Hora");
		TableColumn<Reunion, String> colListaContactos = new TableColumn<Reunion, String>("Contactos de Reunion");

		colNombre.setCellValueFactory(CtrlTablaReuniones.obtenerCallbackNombre());
		colDescripcion.setCellValueFactory(CtrlTablaReuniones.obtenerCallbackDescripcion());
		colFechaHora.setCellValueFactory(CtrlTablaReuniones.obtenerCallbackFechaHora());
		colListaContactos.setCellFactory(CtrlTablaReuniones.obtenerCallbackListaContactos());

		tablaContactos.getColumns().add(colNombre);
		tablaContactos.getColumns().add(colDescripcion);
		tablaContactos.getColumns().add(colFechaHora);
		tablaContactos.getColumns().add(colListaContactos);

		tablaContactos.setItems(CtrlTablaReuniones.obtenerItems());
		setCenter(tablaContactos);
	}
}
