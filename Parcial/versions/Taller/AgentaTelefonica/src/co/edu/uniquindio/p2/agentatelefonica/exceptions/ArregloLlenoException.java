package co.edu.uniquindio.p2.agentatelefonica.exceptions;

public class ArregloLlenoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Se muestra en caso de que un arreglo este lleno
	 * 
	 * @param msg
	 */
	public ArregloLlenoException(String msg) {
		super(msg);
	}
}
