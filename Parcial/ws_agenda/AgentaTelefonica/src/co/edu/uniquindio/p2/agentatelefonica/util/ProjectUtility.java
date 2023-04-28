package co.edu.uniquindio.p2.agentatelefonica.util;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectUtility {

	/**
	 * Obtiene la fecha inicial y final de un mes, es determinada por la fecha
	 * enviada por parámetos y la cantidad de meses a agregar de esta
	 *
	 * @param fecha
	 * @param cantMesesAgregar
	 * @return
	 */
	public static Relacion<LocalDate, LocalDate> obtenerDiaMinimoMaximo(LocalDate fecha, int cantMesesAgregar) {
		LocalDate fechaNueva = fecha.plusMonths(cantMesesAgregar);
		LocalDate fechaMenor = fechaNueva.minusDays(fechaNueva.getDayOfMonth() - 1);
		LocalDate fechaMayor = fechaNueva.plusMonths(1);
		fechaMayor = fechaMayor.minusDays(fechaMayor.getDayOfMonth());
		return new Relacion<LocalDate, LocalDate>(fechaMenor, fechaMayor);
	}

	/**
	 * Determina la cantidad filas en la matriz que tengan todos sus elementos como
	 * null
	 *
	 * @param matrizObjetos
	 * @return
	 */
	public static int obtenerCantFilasTodoNull(Object[][] matrizObjetos) {
		int cantidadAllNull = 0;
		for (Object[] arregloObjetos : matrizObjetos) {
			if (ProjectUtility.arregloTieneTodoNulls(arregloObjetos))
				cantidadAllNull++;
		}
		return cantidadAllNull;
	}

	/**
	 * Determina si un arreglo de objetos tiene todos los objetos null o no
	 *
	 * @param arregloObjetos
	 * @return
	 */
	public static boolean arregloTieneTodoNulls(Object[] arregloObjetos) {
		for (Object objeto : arregloObjetos)
			if (objeto != null)
				return false;
		return true;
	}

	/**
	 * Obtiene el ancho de una matriz sin contar nulls
	 *
	 * @param asda
	 * @return
	 */
	public static int obtenerCantidadMayorArr(Object[] asda) {
		int cantidad = 0;
		for (int i = 0; i < asda.length; i++) {
			if (asda[i] != null)
				cantidad++;
		}
		return cantidad;
	}

	/**
	 * Obtiene el ancho mayor de una matriz de cualquier objeto sin contar nulls
	 *
	 * @param matriz
	 * @return
	 */
	public static int obtenerAnchoMayorMatriz(Object[][] matriz) {
		int cantidad = 0;
		for (int i = 0; i < matriz.length; i++) {
			int canAux = obtenerCantidadMayorArr(matriz[i]);
			if (canAux > cantidad)
				cantidad = canAux;
		}
		return cantidad;
	}

	/**
	 * Determina si una cadena {@code cadena} tiene unas letras especificas
	 * {@code letras} <br>
	 * Usa un stringbuilder con la siguiente condicion en un ciclo que va por cada
	 * caracter de las letras: <br>
	 *
	 * <pre> {@code
	 * (stringbuilder.indexOf(Character.toString(letras.charAt(i))) == -1)
	 * }<br>
	 * </pre>
	 *
	 * El indexof del {@code StringBuilder} retorna un -1 si no se encuentra, y si
	 * no se encuentra se debe de retornar un falso
	 *
	 * @param cadena
	 * @param letras
	 * @return true si contiene todas los caracteres de las letras al menos una vez
	 */
	public static boolean cadenaTieneLetras(String cadena, String letras) {
		StringBuilder stringbuilder = new StringBuilder(cadena.toLowerCase());
		for (int i = 0; i < letras.length(); i++)
			if (stringbuilder.indexOf(Character.toString(letras.toLowerCase().charAt(i))) == -1)
				return false;
		return true;
	}

	/**
	 * Determina si una cadena es capicua o no lo es
	 * 
	 * @param telefono
	 * @return
	 */
	public static boolean esCapicua(String telefono) {
		StringBuilder sb = new StringBuilder(telefono);
		// voltea el string builder que tiene el telefono y lo convierte en un string
		String onofelet = sb.reverse().toString();
		return telefono.equals(onofelet);
	}

	/**
	 * Determina si una fecha esta entre un rango de fechas especificas
	 * 
	 * @param fecha
	 * @param fechaMenor
	 * @param fechaMayor
	 * @return
	 */
	public static boolean tieneFechaEnRango(LocalDate fecha, LocalDate fechaMenor, LocalDate fechaMayor) {
		return fecha != null && (fecha.isAfter(fechaMenor) || fecha.isEqual(fechaMenor))
				&& (fecha.isBefore(fechaMayor) || fecha.isEqual(fechaMayor));
	}

	public static int obtenerMayorTamArrayList(ArrayList<?>... listas) {
		int mayor = Integer.MIN_VALUE;
		for (ArrayList<?> arrayList : listas) {
			int tamArrayList = arrayList.size();
			if (tamArrayList >= mayor)
				mayor = tamArrayList;
		}
		return mayor;
	}

}
