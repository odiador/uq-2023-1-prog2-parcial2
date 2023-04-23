package co.edu.uniquindio.p2.agentatelefonica.controllers;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.ArregloLlenoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.CampoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ContactoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.model.Contacto;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlContacto {
	public static void agregarContacto(String nombre, String alias, String direccion, String telefono, String email) {
		try {
			agregarContactoThrows(nombre, alias, direccion, telefono, email);
			new Alert(AlertType.CONFIRMATION, "El contacto ha sido agregado exitosamente").show();
		} catch (CampoException | ObjetoNoExisteException | ContactoException | ArregloLlenoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void buscarContacto(String nombre, String telefono) {
		try {
			Contacto contactoThrows = buscarContactoThrows(nombre, telefono);
			new Alert(AlertType.CONFIRMATION, contactoThrows.toString()).show();
		} catch (CampoException | ContactoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void eliminarContacto(String nombre, String telefono) {
		try {
			eliminarContactoThrows(nombre, telefono);
			new Alert(AlertType.CONFIRMATION, "El contacto ha sido eliminado con exito").show();
		} catch (ObjetoNoExisteException | ContactoException | CampoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();

		}
	}

	private static void eliminarContactoThrows(String nombre, String telefono)
			throws ObjetoNoExisteException, ContactoException, CampoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(telefono);
		SerializedData data = new SerializedData();
		Contacto contacto = new Contacto(nombre, telefono);
		data.getAgenda().eliminarContacto(contacto);
		data.actualizarAgenda();
	}

	private static void agregarContactoThrows(String nombre, String alias, String direccion, String telefono,
			String email) throws CampoException, ObjetoNoExisteException, ContactoException, ArregloLlenoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(alias);
		Utility.throwIfEmpty(direccion);
		Utility.throwIfEmpty(telefono);
		Utility.throwIfEmpty(email);
		Contacto contacto = new Contacto(nombre, alias, direccion, telefono, email);
		SerializedData data = new SerializedData();
		data.getAgenda().aniadirContacto(contacto);
		data.actualizarAgenda();
	}

	protected static Contacto buscarContactoThrows(String nombre, String telefono)
			throws CampoException, ContactoException {
		Utility.throwIfEmpty(nombre);
		Utility.throwIfEmpty(telefono);
		SerializedData data = new SerializedData();
		Contacto contacto = new Contacto(nombre, telefono);
		Contacto buscarContactoThrows = data.getAgenda().buscarContactoThrows(contacto);
		return buscarContactoThrows;
	}
}
