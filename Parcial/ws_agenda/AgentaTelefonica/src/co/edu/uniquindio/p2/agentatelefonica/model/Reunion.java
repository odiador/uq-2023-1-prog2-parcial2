package co.edu.uniquindio.p2.agentatelefonica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.ArregloLlenoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ContactoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.util.ProjectUtility;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;

public class Reunion implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String nombre;
	private final Contacto[] listaContactos;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private final Nota[] listaNotas;

	/**
	 * Es el constructor de la clase reunion
	 *
	 * @param nombre
	 * @param listaContactos
	 */
	public Reunion(String nombre, String descripcion, LocalDate fecha, LocalTime hora, Contacto[] listaContactos,
			Nota[] listaNotas) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = hora;
		this.listaContactos = listaContactos;
		this.listaNotas = listaNotas;
	}

	/**
	 * Es el constructor de la clase reunion sin atributos
	 */
	public Reunion() {
		this.nombre = null;
		this.listaContactos = new Contacto[0];
		this.listaNotas = new Nota[0];
	}

	/**
	 * Es el constructor de la clase reunion solo con el nombre de la reunion
	 *
	 * @param nombre
	 */
	public Reunion(String nombre) {
		this.nombre = nombre;
		this.listaContactos = new Contacto[0];
		this.listaNotas = new Nota[0];
	}

	/**
	 * Es el constructor de la reunion pero sin contar la lista de contactos
	 *
	 * @param nombre
	 * @param descripcion
	 * @param fechaHora
	 */
	public Reunion(String nombre, String descripcion, LocalDate fecha, LocalTime hora) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hora = hora;
		this.fecha = fecha;
		this.listaContactos = new Contacto[0];
		this.listaNotas = new Nota[0];
	}

	/**
	 * Lista las notas como un objeto de tipo List sin contar las notas que son
	 * nulls
	 * 
	 * @return
	 */
	public List<Nota> listarNotas() {
		return Arrays.stream(listaNotas).filter(nota -> nota != null).collect(Collectors.toList());
	}

	/**
	 * Añade un contacto al arreglo de contactos, en caso de que este lleno se
	 *
	 * @param c
	 * @throws ObjetoNoExisteException en caso de que el contacto {@code c} sea null
	 * @throws ContactoException       en caso de que el contacto ya exista
	 * @throws ArregloLlenoException   en caso de que el arreglo de contactos este
	 *                                 lleno
	 */
	public void aniadirContacto(Contacto c) throws ObjetoNoExisteException, ContactoException, ArregloLlenoException {
		Utility.throwifNull(c, "El contacto no existe");
		if (existeContacto(c))
			throw new ContactoException("El contacto no se pudo agregar (ya existe)");
		int posLibre = buscarPosLibreContacto();
		if (posLibre == -1)
			throw new ArregloLlenoException("La lista de contactos esta llena");
		listaContactos[posLibre] = c;
	}

	/**
	 * Obtiene la primera posicion libre de un contacto, si no se encuentra se
	 * retorna un -1
	 *
	 * @see {@link #buscarPosContacto(Contacto)}
	 * @return -1 si no se encuentra, cualquier otro numero en caso de que si se
	 *         encuentre
	 */
	private int buscarPosLibreContacto() {
		return buscarPosContacto(null);
	}

	/**
	 * Determina si una reunion tiene la fecha en un rango determinado
	 *
	 * @param fechaMenor
	 * @param fechaMayor
	 * @return
	 */
	public boolean esEnRangoFecha(LocalDate fechaMenor, LocalDate fechaMayor) {
		return ProjectUtility.tieneFechaEnRango(fecha, fechaMenor, fechaMayor);
	}

	/**
	 * Determina si el contacto existe o no dependiendo de que si la posicion del
	 * contacto no sea null
	 *
	 * @see {@link #buscarPosContacto(Contacto)}
	 * @param c
	 * @return true si existe
	 */
	public boolean existeContacto(Contacto c) {
		return buscarPosContacto(c) != -1;
	}

	/**
	 * Busca la posicion en la que se encuentra un determinado contacto, usa el
	 * metodo indexOf del arraylist, el cual usa la condicion (c==null ?
	 * get(i)==null : c.equals(get(i))), o -1 en caso de que no se encuentre
	 *
	 * @param c
	 * @return el indice de la posicion del contacto o -1 si no se encuentra
	 */
	private int buscarPosContacto(Contacto c) {
		ArrayList<Contacto> auxListaContactos = Arrays.asList(listaContactos).stream()
				.collect(Collectors.toCollection(ArrayList::new));
		int indexOf = auxListaContactos.indexOf(c);
		return indexOf;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the listaContactos
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene la fecha y hora de la reunion
	 *
	 * @return
	 */
	public LocalDateTime getFechaHora() {
		return LocalDateTime.of(fecha, hora);
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	/**
	 * @return the listaNotas
	 */
	public Nota[] getListaNotas() {
		return listaNotas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reunion other = (Reunion) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return !existe() ? "Reunion [?]"
				: String.format(
						"Reunion [nombre=%s, listaContactos=%s, descripcion=%s, fecha=%s, hora=%s, listaNotas=%s]",
						nombre, Arrays.toString(listaContactos), descripcion, fecha, hora, Arrays.toString(listaNotas));
	}

	private boolean existe() {
		return fecha != null && nombre != null && descripcion != null && hora != null;
	}

}
