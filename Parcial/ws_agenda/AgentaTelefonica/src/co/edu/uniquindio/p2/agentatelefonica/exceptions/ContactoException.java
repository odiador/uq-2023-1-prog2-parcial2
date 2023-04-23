package co.edu.uniquindio.p2.agentatelefonica.exceptions;

public class ContactoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Se ejecuta en caso de que haya algun error con el contacto
	 * 
	 * @param msg
	 */
	public ContactoException(String msg) {
		super(msg);
	}
}
