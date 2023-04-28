package co.edu.uniquindio.p2.agentatelefonica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.NotaException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.util.ProjectUtility;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;

public class Nota implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String codigo;
	private LocalDate fecha;
	private String[] comentarios;
	private CategoriaNota categoria;

	/**
	 * Es el constructor de la clase Nota, tiene los comentarios a agregar
	 *
	 * @param codigo
	 * @param fecha
	 * @param categoria
	 * @param comentarios
	 */
	public Nota(String codigo, LocalDate fecha, CategoriaNota categoria, String... comentarios) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.comentarios = comentarios;
		this.categoria = categoria;
	}

	/**
	 * Es el constructor de la clase Nota
	 *
	 * @param codigo
	 * @param fecha
	 * @param categoria
	 */
	public Nota(String codigo, LocalDate fecha, CategoriaNota categoria) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.comentarios = new String[0];
		this.categoria = categoria;
	}

	/**
	 * Es el constructor de la clase Nota solo con el codigo
	 *
	 * @param codigo
	 */
	public Nota(String codigo) {
		this.codigo = codigo;
		this.comentarios = new String[0];
	}

	/**
	 * Es el constructor de la clase Nota sin atributos
	 */
	public Nota() {
		this.codigo = null;
		this.comentarios = new String[0];
	}

	/**
	 * <b>[PUNTO #3]</b><br>
	 * Determina si la nota esta en un rango de fechas especifica
	 * 
	 * @param fechaMenor
	 * @param fechaMayor
	 * @return
	 */
	public boolean esEnRangoFecha(LocalDate fechaMenor, LocalDate fechaMayor) {
		return ProjectUtility.tieneFechaEnRango(fecha, fechaMenor, fechaMayor);
	}

	/**
	 * <b>[PUNTO #1 B] </b><br>
	 * Este método busca todas las palabras de un comentario que empiecen con una
	 * determinada letra, retorna un arreglo de Strings con los comentarios que
	 * empiezan con esa letra
	 * 
	 * @param letra
	 * @return
	 */
	public String[] calcComentariosEmpiezanLetra(char letra) {
		StringBuilder sb = new StringBuilder();

		for (String cadena : comentarios) {
			if (cadena == null)
				continue;
			// le agrega la cadena y despues un espacio para poder hacer un split despues
			sb.append(cadena);
			sb.append(" ");
		}
		// separa las cadenas por palabra
		String[] arrPalabraComentario = sb.toString().split(" ");
		return Arrays.stream(arrPalabraComentario).filter(comm -> comm.length() >= 1)
				.filter(comm -> comm.charAt(0) == letra).toArray(String[]::new);
	}

	/**
	 * Agrega un comentario a la nota, si ya se encuentra, se marca un error
	 *
	 * @param comentario
	 * @throws ObjetoNoExisteException en caso de que sea null el comentario a
	 *                                 agregar
	 */
	public void agregarComentario(String comentario) throws ObjetoNoExisteException {
		Utility.throwifNull(comentario, "El comentario no pudo ser agregado, no se pudo obtener (null)");
		int pos = buscarPosLibreComentario();
		if (pos == -1) {
			pos = comentarios.length;
			comentarios = Arrays.copyOf(comentarios, pos + 1);
		}
		comentarios[pos] = comentario;
	}

	public void eliminarComentario(String comentario) throws ObjetoNoExisteException, NotaException {
		Utility.throwifNull(comentario, "El comentario no pudo ser agregado, no se pudo obtener (null)");
		int pos = buscarPosComentario(comentario);
		if (pos == -1)
			throw new NotaException("El comentario no se encuentra, no puede ser eliminado");
		comentarios[pos] = null;
	}

	/**
	 * Busca una posicion libre de la lista de comentarios
	 *
	 * @return
	 */
	private int buscarPosLibreComentario() {
		return buscarPosComentario(null);
	}

	/**
	 * Busca la posicion en la que esta un comentario especifico
	 *
	 * @param comentario
	 * @return
	 */
	public int buscarPosComentario(String comentario) {
		ArrayList<String> auxListaContactos = Arrays.asList(comentarios).stream()
				.collect(Collectors.toCollection(ArrayList::new));
		int indexOf = auxListaContactos.indexOf(comentario);
		return indexOf;
	}

	/**
	 * Lista los comentarios de la nota, no lista los que son null
	 *
	 * @return
	 */
	public ArrayList<String> listarComentarios() {
		return Arrays.asList(comentarios).stream().filter(comentario -> comentario != null)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Agrega comentarios a la nota, muestra errores si no se dan las cosas
	 * correctamente
	 *
	 * @param comentarios
	 * @throws ObjetoNoExisteException en caso de que algo sea null
	 * @throws NotaException           si no hay comentarios o si ya existe un
	 *                                 comentario
	 */
	public void agregarComentarios(String... comentarios) throws ObjetoNoExisteException, NotaException {
		Utility.throwifNull(comentarios, "No hay comentarios a agregar");
		if (comentarios.length == 0)
			throw new NotaException("No hay comentarios a agregar");
		for (String cadaComentario : comentarios)
			agregarComentario(cadaComentario);
	}

	/**
	 * Determina si un comentario existe o no en la lista de comentarios
	 *
	 * @param comentario
	 * @return
	 */
	public boolean existeComentario(String comentario) {
		for (int i = 0; i < comentarios.length; i++) {
			String cadaComentario = comentarios[i];
			if (cadaComentario.equals(comentario))
				return true;
		}
		return false;
	}

	public String getCodigo() {
		return codigo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public CategoriaNota getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaNota categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return String.format("Nota [codigo=%s, fecha=%s, comentarios=%s, categoria=%s]", codigo, fecha,
				Arrays.toString(comentarios), categoria);
	}

}
