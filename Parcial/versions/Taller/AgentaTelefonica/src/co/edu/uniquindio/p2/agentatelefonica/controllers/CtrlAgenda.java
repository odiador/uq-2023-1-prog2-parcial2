package co.edu.uniquindio.p2.agentatelefonica.controllers;

import java.util.Optional;

import co.edu.uniquindio.p2.agentatelefonica.application.Main;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.CampoException;
import co.edu.uniquindio.p2.agentatelefonica.model.Agenda;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import co.edu.uniquindio.p2.agentatelefonica.views.scenes.EscenaCrearAgenda;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class CtrlAgenda {

	/**
	 * Este metodo se ejecuta al inicio, para decidir si crear, sobreescribir o no
	 * hacer nada con respecto al archivo de la agenda
	 * 
	 * @param stage
	 */
	public static void crearData(Stage stage) {
		SerializedData data = new SerializedData();
		try {
			data.leerObjeto();
			preguntarCrearAgenda(stage, "La agenda ya existe, deseas sobreescribirla?");

		} catch (Exception e) {
			CtrlAgenda.irACrearAgenda(stage);
		}
	}

	/**
	 * Crea una agenda a partir de sus atributos, en caso de que haya un campo vacío
	 * muestra una advertencia y si la crea, se muestra la ventana principal
	 * 
	 * @param stage
	 * @param stageMain
	 * @param nombre
	 * @param cantContactosString
	 * @param cantGruposString
	 * @param cantReunionesString
	 */
	public static void crearAgenda(Stage stage, Stage stageMain, String nombre, String cantContactosString,
			String cantGruposString, String cantReunionesString) {
		try {
			crearAgendaThrows(nombre, cantContactosString, cantGruposString, cantReunionesString);
			stage.close();
			stageMain.setTitle(new SerializedData().getAgenda().getNombre() + " | Juan Manuel Amador Roa");
			stageMain.show();
		} catch (CampoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Indica los tanto los contactos ocupados como los libres de la agenda
	 */
	public static void huecosLibres() {
		SerializedData data = new SerializedData();
		int huecosLibres = data.getAgenda().huecosLibres();
		int ocupados = data.getAgenda().cantidadOcupados();
		StringBuilder sringBuilder = new StringBuilder();
		sringBuilder.append("La cantidad de contactos ocupados son: ");
		sringBuilder.append(ocupados);
		sringBuilder.append(" y de libres son: ");
		sringBuilder.append(huecosLibres);
		Alert alert = new Alert(AlertType.INFORMATION, sringBuilder.toString());
		alert.show();
	}

	/**
	 * Crea una agenda y la guarda en un archivo .dat
	 * 
	 * @param nombre
	 * @param cantContactosString
	 * @param cantGruposString
	 * @param cantReunionesString
	 * @throws CampoException
	 */
	private static void crearAgendaThrows(String nombre, String cantContactosString, String cantGruposString,
			String cantReunionesString) throws CampoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(cantContactosString);
		Utility.throwIfEmpty(cantGruposString);
		Utility.throwIfEmpty(cantReunionesString);
		int cantContactos = Utility.pasarEnteroThrows(cantContactosString);
		int cantGrupos = Utility.pasarEnteroThrows(cantGruposString);
		int cantReuniones = Utility.pasarEnteroThrows(cantReunionesString);
		SerializedData data = new SerializedData();
		data.setAgenda(new Agenda(nombre, cantContactos, cantReuniones, cantGrupos));
		data.actualizarAgenda();
	}

	/**
	 * En caso de que se quiera preguntar si se quiere crear la agenda se ejecuta
	 * este metodo, y si se decide que no, se muestra la ventana principal
	 * 
	 * @param stage
	 * @param msg
	 */
	private static void preguntarCrearAgenda(Stage stage, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION, msg, ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> crearAgenda = alert.showAndWait();
		ButtonType resultadoCrear = crearAgenda.orElse(null);
		if (resultadoCrear == ButtonType.YES)
			irACrearAgenda(stage);
		else {
			stage.setTitle(new SerializedData().getAgenda().getNombre() + " | Juan Manuel Amador Roa");
			stage.show();
		}
	}

	/**
	 * Crea una nueva stage para crear la agenda, de la cual, cuando se termine de
	 * crear lleva al usuario a la ventana principal {@code stageMain}
	 * 
	 * @param stageMain
	 */
	static void irACrearAgenda(Stage stageMain) {
		Stage stage = new Stage();
		EscenaCrearAgenda escenaCrearAgenda = new EscenaCrearAgenda(stage, stageMain);
		stage.setTitle("Agenda - Agregar Agenda | Juan Manuel Amador Roa");
		stage.setScene(escenaCrearAgenda);
		escenaCrearAgenda.getStylesheets().add(Main.applicationCss);
		stage.show();
	}

}
