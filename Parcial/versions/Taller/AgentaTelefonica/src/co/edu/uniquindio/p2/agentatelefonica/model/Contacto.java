package co.edu.uniquindio.p2.agentatelefonica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.GrupoException;

public class Contacto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String nombre;
	private String alias;
	private String direccion;
	private final String telefono;
	private String email;
	private Grupo[] gruposALosQuePertenece;

	/**
	 * Es el constructor de la clase contacto
	 * 
	 * @param nombre
	 * @param alias
	 * @param direccion
	 * @param telefono
	 * @param email
	 * @see
	 *      <li>{@link #Contacto()}
	 *      <li>{@link #Contacto(String, String, String, String, String, Grupo[])}
	 */
	public Contacto(String nombre, String alias, String direccion, String telefono, String email,
			Grupo[] gruposALosQuePertenece) {
		super();
		this.nombre = nombre;
		this.alias = alias;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.gruposALosQuePertenece = gruposALosQuePertenece;
	}

	/**
	 * Es el constructor de la clase contacto
	 * 
	 * @param nombre
	 * @param alias
	 * @param direccion
	 * @param telefono
	 * @param email
	 * @see
	 *      <li>{@link #Contacto()}
	 *      <li>{@link #Contacto(String, String, String, String, String, Grupo[])}
	 */
	public Contacto(String nombre, String alias, String direccion, String telefono, String email) {
		super();
		this.nombre = nombre;
		this.alias = alias;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		createGroupArrIfNull();
	}

	public Contacto(String nombre, String telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public boolean exists() {
		return nombre != null && telefono != null;
	}

	private void createGroupArrIfNull() {
		if (gruposALosQuePertenece == null)
			this.gruposALosQuePertenece = new Grupo[0];
	}

	public void addGrupo(Grupo grupo) {
		int pos = obtenerPosLibreGrupos();
		if (pos == -1) {
			pos = gruposALosQuePertenece.length;
			gruposALosQuePertenece = Arrays.copyOf(gruposALosQuePertenece, pos + 1);
			gruposALosQuePertenece[pos] = grupo;
		} else
			gruposALosQuePertenece[pos] = grupo;
	}

	public void removeGrupo(Grupo grupo) throws GrupoException {
		int posGrupo = buscarPosGrupo(grupo);
		if (posGrupo == -1)
			throw new GrupoException("El grupo no se ha encontrado, no se puede eliminar");
		gruposALosQuePertenece[posGrupo] = null;
	}

	private int obtenerPosLibreGrupos() {
		return buscarPosGrupo(null);
	}

	public boolean hayPosLibreGrupo() {
		return obtenerPosLibreGrupos() != -1;
	}

	public boolean existeGrupo(Grupo grupo) {
		return buscarPosGrupo(grupo) != -1;
	}

	public int buscarPosGrupo(Grupo grupo) {
		return ((ArrayList<Grupo>) Arrays.asList(gruposALosQuePertenece)).indexOf(grupo);
	}

	/**
	 * Es el constructor vacio de la clase contacto sin parametros
	 * 
	 * @see
	 *      <li>{@link #Contacto(String, String, String, String, String)}
	 *      <li>{@link #Contacto(String, String, String, String, String, Grupo[])}
	 */
	public Contacto() {
		this.nombre = null;
		this.telefono = null;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the gruposALosQuePertenece
	 */
	public Grupo[] getGruposALosQuePertenece() {
		return gruposALosQuePertenece;
	}

	/**
	 * @param gruposALosQuePertenece the gruposALosQuePertenece to set
	 */
	public void setGruposALosQuePertenece(Grupo[] gruposALosQuePertenece) {
		this.gruposALosQuePertenece = gruposALosQuePertenece;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Contacto [nombre=%s, alias=%s, direccion=%s, telefono=%s, email=%s, gruposALosQuePertenece=%s]",
				nombre, alias, direccion, telefono, email, Arrays.toString(gruposALosQuePertenece));
	}
}
