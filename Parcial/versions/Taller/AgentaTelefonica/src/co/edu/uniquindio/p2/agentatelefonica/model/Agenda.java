package co.edu.uniquindio.p2.agentatelefonica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.ArregloLlenoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ContactoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ReunionException;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;

public class Agenda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Reunion[] listaReuniones;
	private Grupo[] listaGrupos;

	public Agenda(String nombre, int listaContactosLength, int listaReunionesLength, int listaGruposLength) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[listaContactosLength];
		this.listaReuniones = new Reunion[listaReunionesLength];
		this.listaGrupos = new Grupo[listaGruposLength];
	}

	public Agenda(String nombre) {
		this(nombre, 0, 0, 0);
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
	 * Agrega una reunion, muestra errores si no pasan las cosas como deberian de
	 * pasar
	 * 
	 * @param reunion
	 * @throws ObjetoNoExisteException
	 * @throws ReunionException
	 * @throws ArregloLlenoException
	 */
	public void agregarReunion(Reunion reunion)
			throws ObjetoNoExisteException, ReunionException, ArregloLlenoException {
		Utility.throwifNull(reunion, "La reunion no existe");
		if (buscarReunion(reunion) != null)
			throw new ReunionException("La reunion ya existe, no se puede agregar");
		int pos = buscarPosLibreReunion();
		if (pos == -1)
			throw new ArregloLlenoException("La lista de reuniones ya esta llena");
		listaReuniones[pos] = reunion;
	}

	/**
	 * Busca la posicion en la que una reunion esta libre
	 * 
	 * @return
	 */
	private int buscarPosLibreReunion() {
		return buscarPosReunion(null);
	}

	/**
	 * Busca una reunion, retorna null si no se encuentra
	 * 
	 * @param reunion
	 */
	private Reunion buscarReunion(Reunion reunion) {
		int pos = buscarPosReunion(reunion);
		if (pos == -1)
			return null;
		return listaReuniones[pos];
	}

	private int buscarPosReunion(Reunion reunion) {
		ArrayList<Reunion> auxListaReuniones = Arrays.asList(listaReuniones).stream()
				.collect(Collectors.toCollection(ArrayList::new));
		int indexOf = auxListaReuniones.indexOf(reunion);
		return indexOf;
	}

	/**
	 * Busca un contacto a partir del contacto, si no se encuentra se retorna un
	 * null
	 * 
	 * @param contacto
	 * @return el contacto, si no se encuentra null
	 */
	public Contacto buscarContacto(Contacto contacto) {
		for (Contacto cadaContacto : listaContactos)
			if (contacto.equals(cadaContacto))
				return cadaContacto;
		return null;
	}

	/**
	 * Busca un contacto y si no se encuentra se muestra un error
	 * 
	 * @param contacto
	 * @return el contacto encontrado
	 * @throws ContactoException
	 */
	public Contacto buscarContactoThrows(Contacto contacto) throws ContactoException {
		Contacto contactoEncontrado = buscarContacto(contacto);
		if (contactoEncontrado != null)
			return contactoEncontrado;
		throw new ContactoException("El contacto no fue encontrado");
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
	 * Elimina el contacto de la agenda, muestra errores en caso de que no se pueda
	 * eliminar
	 * 
	 * @param c
	 * @throws ObjetoNoExisteException en caso de que el contacto enviado sea null
	 * @throws ContactoException       en caso de que no exista el contacto
	 */
	public void eliminarContacto(Contacto c) throws ObjetoNoExisteException, ContactoException {
		Utility.throwifNull(c, "El contacto no existe");
		int posContacto = buscarPosContacto(c);
		if (posContacto == -1)
			throw new ContactoException("El contacto no pudo ser eliminado (no existe)");
		listaContactos[posContacto] = null;
	}

	/**
	 * Determina si la agenda esta llena o no
	 * 
	 * @return
	 */
	public boolean agendaLlena() {
		return !existeContacto(null);
	}

	/**
	 * Indica cuantos contactos más podemos meter.
	 * 
	 * @return la cantidad de huecos libres
	 */
	public int huecosLibres() {
		int contador = 0;
		for (Contacto contacto : listaContactos)
			if (contacto == null)
				contador++;
		return contador;
	}

	/**
	 * Obtiene la cantidad de espacios del contacto
	 * 
	 * @return
	 */
	public int cantidadEspaciosContactos() {
		return listaContactos.length;
	}

	/**
	 * Obtiene la cantidad de contactos ocupados
	 * 
	 * @return
	 */
	public int cantidadOcupados() {
		return cantidadEspaciosContactos() - huecosLibres();
	}

	/**
	 * Obtiene la lista de contactos
	 * 
	 * @return
	 */
	public ArrayList<Contacto> listarContactos() {
		List<Contacto> listaAux = Arrays.asList(listaContactos);
		return listaAux.stream().filter(contacto -> contacto != null).collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<Reunion> listarReuniones() {
		List<Reunion> listaAux = Arrays.asList(listaReuniones);
		return listaAux.stream().filter(reunion -> reunion != null).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Obtiene el nombre de la agenda
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre de la agenda
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la lista de contactos de la agenda
	 * 
	 * @return
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	/**
	 * Cambia la lista de contactos de la agenda
	 * 
	 * @param listaContactos
	 */
	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	/**
	 * Obtiene la lista de reuniones de la agenda
	 * 
	 * @return
	 */
	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}

	/**
	 * Obtiene la lista de reuniones de la agenda
	 * 
	 * @param listaReuniones
	 */
	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}

	/**
	 * Obtiene la lista de grupos de la agenda
	 * 
	 * @return
	 */
	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}

	/**
	 * 
	 * Cambia la lista de grupos de la agenda
	 * 
	 * @param listaGrupos
	 */
	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
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
		Agenda other = (Agenda) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format("Agenda [nombre=%s, listaContactos=%s, listaReuniones=%s, listaGrupos=%s]", nombre,
				Arrays.toString(listaContactos), Arrays.toString(listaReuniones), Arrays.toString(listaGrupos));
	}

	/**
	 * Elimina una reunion de la agenda, muestra errores si no se puede
	 * 
	 * @param reunion
	 * @throws ReunionException
	 * @throws ObjetoNoExisteException
	 */
	public void eliminarReunion(Reunion reunion) throws ReunionException, ObjetoNoExisteException {
		Utility.throwifNull(reunion, "La reunion enviada no funciona para eliminar");
		int pos = buscarPosReunion(reunion);
		if (pos == -1)
			throw new ReunionException("La reunion no existe, no se puede eliminar");
		listaReuniones[pos] = null;
	}
}
