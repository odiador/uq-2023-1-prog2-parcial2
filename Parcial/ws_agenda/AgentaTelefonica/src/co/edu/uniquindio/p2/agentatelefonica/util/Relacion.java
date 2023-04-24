package co.edu.uniquindio.p2.agentatelefonica.util;

public class Relacion<T1, T2> {

	private T1 valor1;
	private T2 valor2;

	public Relacion(T1 valor1, T2 valor2) {
		this.valor1 = valor1;
		this.valor2 = valor2;
	}

	/**
	 * @return the valor1
	 */
	public T1 getValor1() {
		return valor1;
	}

	/**
	 * @param valor1 the valor1 to set
	 */
	public void setValor1(T1 valor1) {
		this.valor1 = valor1;
	}

	/**
	 * @return the valor2
	 */
	public T2 getValor2() {
		return valor2;
	}

	/**
	 * @param valor2 the valor2 to set
	 */
	public void setValor2(T2 valor2) {
		this.valor2 = valor2;
	}

	@Override
	public String toString() {
		return String.format("Relacion [valor1=%s, valor2=%s]", valor1, valor2);
	}

}
