package co.edu.uniquindio.p2.agentatelefonica.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.p2.agentatelefonica.model.Agenda;

public class SerializedData {
	private static final String RUTA = "src/co/edu/uniquindio/p2/agentatelefonica/controllers/info.dat";
	private Agenda agenda;

	public SerializedData() {
		try {
			leerObjeto();
		} catch (Exception e) {
		}
	}

	protected void escribirObjeto() throws Exception {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(RUTA));
		try {
			objectOutputStream.writeObject(agenda);
			objectOutputStream.close();
		} catch (Exception e) {
			objectOutputStream.close();
			throw e;
		}
	}

	protected void leerObjeto() throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(RUTA));
		try {
			this.agenda = (Agenda) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			objectInputStream.close();
			throw e;
		}
	}

	public void actualizarAgenda() {
		try {
			escribirObjeto();
		} catch (Exception e) {

		}
	}

	/**
	 * @return the agenda
	 */
	public Agenda getAgenda() {
		return agenda;
	}

	/**
	 * @param agenda the agenda to set
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	@Override
	public String toString() {
		return String.format("SerializedData [agenda=%s]", agenda);
	}

}
