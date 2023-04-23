package co.edu.uniquindio.p2.agentatelefonica.exceptions;

public class NotaException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Se ejecuta en caso de que haya algun error con la nota
	 *
	 * @param msg
	 */
	public NotaException(String msg) {
		super(msg);
	}
}
