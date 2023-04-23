package co.edu.uniquindio.p2.agentatelefonica.views.internal;

import co.edu.uniquindio.p2.agentatelefonica.controllers.CtrlTablaContactos;
import co.edu.uniquindio.p2.agentatelefonica.model.Contacto;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PanelVerContactos extends BorderPane {
	public PanelVerContactos() {
		TableView<Contacto> tablaContactos = new TableView<Contacto>();
		TableColumn<Contacto, String> colNombre = new TableColumn<Contacto, String>("Nombre");
		TableColumn<Contacto, String> colAlias = new TableColumn<Contacto, String>("Alias");
		TableColumn<Contacto, String> colDireccion = new TableColumn<Contacto, String>("Direccion");
		TableColumn<Contacto, String> colTelefono = new TableColumn<Contacto, String>("Telefono");
		TableColumn<Contacto, String> colEmail = new TableColumn<Contacto, String>("Email");

		colNombre.setCellValueFactory(CtrlTablaContactos.obtenerCallbackNombre());
		colAlias.setCellValueFactory(CtrlTablaContactos.obtenerCallbackAlias());
		colDireccion.setCellValueFactory(CtrlTablaContactos.obtenerCallbackDireccion());
		colTelefono.setCellValueFactory(CtrlTablaContactos.obtenerCallbackTelefono());
		colEmail.setCellValueFactory(CtrlTablaContactos.obtenerCallbackEmail());

		tablaContactos.getColumns().add(colNombre);
		tablaContactos.getColumns().add(colAlias);
		tablaContactos.getColumns().add(colDireccion);
		tablaContactos.getColumns().add(colTelefono);
		tablaContactos.getColumns().add(colEmail);

		tablaContactos.setItems(CtrlTablaContactos.obtenerItems());
		setCenter(tablaContactos);

	}
}
