package co.edu.uniquindio.p2.agentatelefonica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.ArregloLlenoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ContactoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.util.Utility;

public class Grupo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Categoria categoria;
	private Contacto[] listaContactos;

	/**
	 * Es el constructor del grupo con su nombre y categoria
	 * 
	 * @param nombre
	 * @param categoria
	 */
	public Grupo(String nombre, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.listaContactos = new Contacto[10];
	}

	/**
	 * Es el constructor del grupo vacio
	 */
	public Grupo() {

	}

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

	public boolean hayHuecosLibres() {
		return huecosLibres() != 0;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the listaContactos
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	/**
	 * @param listaContactos the listaContactos to set
	 */
	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
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
		Grupo other = (Grupo) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", categoria=" + categoria + ", listaContactos="
				+ Arrays.toString(listaContactos) + "]";
	}

}
