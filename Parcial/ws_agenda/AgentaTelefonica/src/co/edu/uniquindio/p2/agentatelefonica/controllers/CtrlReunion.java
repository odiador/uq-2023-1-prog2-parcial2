package co.edu.uniquindio.p2.agentatelefonica.controllers;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.ArregloLlenoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.CampoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ReunionException;
import co.edu.uniquindio.p2.agentatelefonica.model.Reunion;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlReunion {
	public static void anadirReunion(String nombre, String descripcion, LocalDate fecha, String horas, String minutos) {
		try {
			anadirReunionThrows(nombre, descripcion, fecha, horas, minutos);
			new Alert(AlertType.CONFIRMATION, "La reunion ha sido agregada con exito").show();
		} catch (CampoException | ObjetoNoExisteException | ReunionException | ArregloLlenoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void anadirReunionThrows(String nombre, String descripcion, LocalDate fecha, String horas,
			String minutos) throws CampoException, ObjetoNoExisteException, ReunionException, ArregloLlenoException {
		Utility.throwifNull(fecha, "Recuerda llenar la fecha");

		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(descripcion);
		Utility.throwIfEmpty(minutos);
		Utility.throwIfEmpty(horas);

		int horasNum = Utility.pasarEnteroThrows(horas);
		int minutosNum = Utility.pasarEnteroThrows(minutos);
		try {
			LocalTime localTime = LocalTime.of(horasNum, minutosNum);
			LocalDateTime fechaHora = LocalDateTime.of(fecha, localTime);
			Reunion reunion = new Reunion(nombre, descripcion, fechaHora);
			SerializedData data = new SerializedData();
			data.getAgenda().agregarReunion(reunion);
			data.actualizarAgenda();
		} catch (DateTimeException e) {
			throw new ObjetoNoExisteException("Organiza bien la fecha y la hora");
		}
	}

	/**
	 * Elimina una reunion con su nombre, en caso de que pase algo no deseado se
	 * suelta un error
	 * 
	 * @param nombreReunion
	 * @throws CampoException
	 * @throws ReunionException
	 * @throws ObjetoNoExisteException
	 */
	public static void eliminarReunionThrows(String nombreReunion)
			throws CampoException, ReunionException, ObjetoNoExisteException {
		Utility.throwIfEmpty(nombreReunion);
		SerializedData data = new SerializedData();
		Reunion reunion = new Reunion(nombreReunion);
		data.getAgenda().eliminarReunion(reunion);
		data.actualizarAgenda();
	}

	/**
	 * Elimina una reunion con su nombre, en caso de que pase algo no deseado
	 * muestra una alerta
	 * 
	 * @param nombreReunion
	 */
	public static void eliminarReunion(String nombreReunion) {
		try {
			eliminarReunionThrows(nombreReunion);
			new Alert(AlertType.CONFIRMATION, "La reunion de nombre " + nombreReunion + " ha sido eliminada").show();
		} catch (CampoException | ReunionException | ObjetoNoExisteException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}
}
