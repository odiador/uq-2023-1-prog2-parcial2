package co.edu.uniquindio.p2.agentatelefonica.exceptions;

public class GrupoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Se ejecuta en caso de que haya algun error con el grupo
	 * 
	 * @param msg
	 */
	public GrupoException(String msg) {
		super(msg);
	}
}
