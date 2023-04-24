package co.edu.uniquindio.p2.agentatelefonica.util;

import java.time.LocalDate;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.CampoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Utility {

	public static void setAsNumberTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
					final ContextMenu menu = new ContextMenu();
					menu.getItems().add(new MenuItem("Este campo solo puede tener numeros"));
					menu.show(tf, Side.BOTTOM, 0, 0);

					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
						menu.hide();
					}));
					timeline.play();
				}
			}
		});
	}

	public static void setMaximumTextLength(TextField tf, int tamanio) {
		tf.setTextFormatter(new TextFormatter<String>(cambio -> {
			if (cambio.isContentChange()) {
				if (cambio.getControlNewText().length() > tamanio) {
					final ContextMenu menu = new ContextMenu();
					menu.getItems().add(new MenuItem("Este campo tiene maximo \n" + tamanio + " caracteres"));
					menu.show(cambio.getControl(), Side.BOTTOM, 0, 0);

					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
						menu.hide();
					}));
					timeline.play();
					return null;
				}
			}
			return cambio;
		}));
	}

	public static HBox generarHBox(String msg, Node... nodos) {
		Label label = new Label(msg);
		label.setId("label");
		HBox hbox = new HBox(20, label);
		hbox.getChildren().addAll(nodos);
		hbox.setId("centered-box");
		return hbox;
	}

	public static HBox generarHBox(int spacing, String msg, Node... nodos) {
		Label label = new Label(msg);
		label.setId("label");
		HBox hbox = new HBox(spacing, label);
		hbox.getChildren().addAll(nodos);
		hbox.setId("centered-box");
		return hbox;
	}

	public static void throwIfEmpty(String nombre, String msg) throws CampoException {
		if (nombre.isEmpty())
			throw new CampoException(msg);
	}

	public static void throwIfEmpty(String nombre) throws CampoException {
		throwIfEmpty(nombre, "Recuerda llenar todos los campos");
	}

	/**
	 * Intenta pasar una cadena a un entero, si no se puede se muestra un error
	 *
	 * @param cadena
	 * @return
	 * @throws CampoException
	 */
	public static int pasarEnteroThrows(String cadena) throws CampoException {
		try {
			return Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			throw new CampoException("Recuerda solo colocar numeros en campos numericos");
		}
	}

	/**
	 * Muestra un error si un objeto es null
	 *
	 * @param object
	 * @param msg
	 * @throws ObjetoNoExisteException
	 */
	public static void throwifNull(Object object, String msg) throws ObjetoNoExisteException {
		if (object == null)
			throw new ObjetoNoExisteException(msg);
	}

	/**
	 * Muestra un error si un arreglo tiene nulls con un mensaje especifico
	 *
	 * @param object
	 * @param msg
	 * @throws ObjetoNoExisteException
	 */
	public static void throwifArrHasNulls(Object[] object, String msg) throws ObjetoNoExisteException {
		for (Object object2 : object)
			throwifNull(object2, msg);
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
	 * Obtiene el ancho mayor de una matriz de cualquier objeto sin contar nulls
	 *
	 * @param matriz
	 * @return
	 */
	public static int obtenerAnchoMayorMatriz(Object[][] matriz) {
		int cantidad = 0;
		for (int i = 0; i < matriz.length; i++) {
			int canAux = Utility.obtenerCantidadMayorArr(matriz[i]);
			if (canAux > cantidad)
				cantidad = canAux;
		}
		return cantidad;
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
	 * Determina la cantidad filas en la matriz que tengan todos sus elementos como
	 * null
	 *
	 * @param matrizObjetos
	 * @return
	 */
	public static int obtenerCantFilasTodoNull(Object[][] matrizObjetos) {
		int cantidadAllNull = 0;
		for (Object[] arregloObjetos : matrizObjetos) {
			if (arregloTieneTodoNulls(arregloObjetos))
				cantidadAllNull++;
		}
		return cantidadAllNull;
	}
}
