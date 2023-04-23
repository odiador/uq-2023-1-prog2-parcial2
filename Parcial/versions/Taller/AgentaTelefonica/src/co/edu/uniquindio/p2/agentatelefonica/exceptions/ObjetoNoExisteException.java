package co.edu.uniquindio.p2.agentatelefonica.exceptions;

public class ObjetoNoExisteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase ObjetoNoExisteException, se ejecuta cuando un
	 * objeto no existe (null)
	 * 
	 * @param msg
	 */
	public ObjetoNoExisteException(String msg) {
		super(msg);
	}
}
