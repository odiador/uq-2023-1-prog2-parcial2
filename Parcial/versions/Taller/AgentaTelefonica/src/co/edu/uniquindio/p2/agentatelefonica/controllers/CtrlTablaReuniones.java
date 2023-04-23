package co.edu.uniquindio.p2.agentatelefonica.controllers;

import java.time.format.DateTimeFormatter;

import co.edu.uniquindio.p2.agentatelefonica.model.Reunion;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CtrlTablaReuniones {
	public static Callback<CellDataFeatures<Reunion, String>, ObservableValue<String>> obtenerCallbackNombre() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getNombre());
	}

	public static Callback<CellDataFeatures<Reunion, String>, ObservableValue<String>> obtenerCallbackDescripcion() {
		return data -> new ReadOnlyStringWrapper(data.getValue().getDescripcion());
	}

	public static Callback<CellDataFeatures<Reunion, String>, ObservableValue<String>> obtenerCallbackFechaHora() {
		return data -> new ReadOnlyStringWrapper(
				data.getValue().getFechaHora().format(DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yy")));
	}

	public static Callback<TableColumn<Reunion, String>, TableCell<Reunion, String>> obtenerCallbackListaContactos() {
		return new Callback<TableColumn<Reunion, String>, TableCell<Reunion, String>>() {

			final @Override public TableCell<Reunion, String> call(TableColumn<Reunion, String> param) {
				TableCell<Reunion, String> cell = new TableCell<Reunion, String>() {

					@Override
					protected void updateItem(String arg0, boolean arg1) {
						super.updateItem(arg0, arg1);
						if (arg1) {
							setText(null);
						} else {
							setId("btn-tabla");
							setOnMouseReleased(evt -> {

							});
							setText("Ver contenido");
						}
					}
				};
				return cell;
			}
		};
	}

	public static ObservableList<Reunion> obtenerItems() {
		SerializedData data = new SerializedData();
		return FXCollections.observableArrayList(data.getAgenda().listarReuniones());
	}
}
