package co.edu.uniquindio.p2.agentatelefonica.controllers;

import co.edu.uniquindio.p2.agentatelefonica.model.Contacto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CtrlTablaContactos {
	public static Callback<CellDataFeatures<Contacto, String>, ObservableValue<String>> obtenerCallbackNombre() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getNombre());
	}

	public static Callback<CellDataFeatures<Contacto, String>, ObservableValue<String>> obtenerCallbackAlias() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getAlias());
	}

	public static Callback<CellDataFeatures<Contacto, String>, ObservableValue<String>> obtenerCallbackEmail() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getEmail());
	}

	public static Callback<CellDataFeatures<Contacto, String>, ObservableValue<String>> obtenerCallbackTelefono() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getTelefono());
	}

	public static Callback<CellDataFeatures<Contacto, String>, ObservableValue<String>> obtenerCallbackDireccion() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getDireccion());
	}

	public static ObservableList<Contacto> obtenerItems() {
		SerializedData data = new SerializedData();
		return FXCollections.observableArrayList(data.getAgenda().listarContactos());
	}
}
