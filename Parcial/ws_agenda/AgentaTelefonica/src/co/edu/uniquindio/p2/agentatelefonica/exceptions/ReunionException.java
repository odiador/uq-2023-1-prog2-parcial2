package co.edu.uniquindio.p2.agentatelefonica.exceptions;

public class ReunionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Se ejecuta en caso de que haya algun error con el contacto
	 * 
	 * @param msg
	 */
	public ReunionException(String msg) {
		super(msg);
	}
}
