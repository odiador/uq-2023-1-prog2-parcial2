package co.edu.uniquindio.p2.agentatelefonica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.ArregloLlenoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ContactoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ReunionException;
import co.edu.uniquindio.p2.agentatelefonica.util.ProjectUtility;
import co.edu.uniquindio.p2.agentatelefonica.util.Relacion;
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

	/**
	 * Es el constructor de la agenda
	 *
	 * @param nombre
	 * @param listaContactosLength
	 * @param listaReunionesLength
	 * @param listaGruposLength
	 */
	public Agenda(String nombre, int listaContactosLength, int listaReunionesLength, int listaGruposLength) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[listaContactosLength];
		this.listaReuniones = new Reunion[listaReunionesLength];
		this.listaGrupos = new Grupo[listaGruposLength];
	}

	/**
	 * Es el constructor de la agenda solo con el nombre, todas las listas tienen
	 * tamanio 0
	 *
	 * @param nombre
	 */
	public Agenda(String nombre) {
		this(nombre, 0, 0, 0);
	}

	/**
	 * <b>[PUNTO #2]</b><br>
	 * Filtra los contactos por grupo, recibe como parámetro un objeto Grupo y
	 * devuelve una lista de contactos que pertenecen a ese grupo
	 * 
	 * @param g
	 * @return
	 */
	public List<Contacto> buscarContactosGrupo(Grupo g) {
		return Arrays.stream(listaContactos).filter(contacto -> contacto != null)
				.filter(contacto -> contacto.perteneceAlGrupo(g)).collect(Collectors.toList());
	}

	/**
	 * <b>[PUNTO #3]</b><br>
	 * Retorna una matriz con las notas cada fila representa un conjunto de notas.
	 * <li>Fila 0 (representa a las notas que hacen parte de las reuniones que estén
	 * entre la fecha 01-11-2022 y 30-11-2022)
	 * <li>Fila 1 (representa a las notas que hacen parte de las reuniones que estén
	 * entre la fecha 01-12-2022 y 31-12-2022)
	 * <li>Fila 2 (representa a las notas que hacen parte de las reuniones que estén
	 * entre la fecha 01-01-2022 y 30-12-2022)
	 * 
	 * Obtiene la matriz de notas que estan en 3 rangos diferentes
	 * 
	 * @return
	 */
	public Nota[][] obtenerMatrizEnRangoFechas() {
		// obtiene las notas que tienen las reuniniones en un rango de fechas
		// especificas
		ArrayList<Nota> notasReunionFecha1 = obtenerArrNotasReunionFecha(LocalDate.of(2022, 11, 1),
				LocalDate.of(2022, 11, 30));
		ArrayList<Nota> notasReunionFecha2 = obtenerArrNotasReunionFecha(LocalDate.of(2022, 12, 1),
				LocalDate.of(2022, 12, 31));
		ArrayList<Nota> notasReunionFecha3 = obtenerArrNotasReunionFecha(LocalDate.of(2022, 1, 1),
				LocalDate.of(2022, 12, 30));
		// obtiene la matriz de las notas con el ancho maximo necesario
		Nota[][] matrizNotasReunionFecha = obtenerMatrizNotasTamMaximo(notasReunionFecha1, notasReunionFecha2,
				notasReunionFecha3);
		return matrizNotasReunionFecha;
	}

	/**
	 * <b>[PUNTO #4]</b><br>
	 * Busca los contactos que tengan un numero de telefono que empiece por un
	 * sufijo
	 * 
	 * @param prefijo
	 * @return
	 */
	public List<Contacto> buscarContactosNumeroPrefijo(String prefijo) {
		return Arrays.stream(listaContactos).filter(contacto -> contacto != null)
				.filter(contacto -> contacto.tieneTelefonoPrefijo(prefijo)).collect(Collectors.toList());
	}

	/**
	 * Obtiene la matriz de las notas con el ancho maximo necesario, la cantidad de
	 * arraylist enviada no puede ser 0
	 * 
	 * @param matrizNotasReunionFechas
	 * @return
	 */
	@SafeVarargs	
	private static Nota[][] obtenerMatrizNotasTamMaximo(ArrayList<Nota>... matrizNotasReunionFechas) {
		int mayorTamArrayList = ProjectUtility.obtenerMayorTamArrayList(matrizNotasReunionFechas);
		Nota[][] matrizNotasReunionFecha = new Nota[mayorTamArrayList][matrizNotasReunionFechas.length];
		for (int i = 0; i < matrizNotasReunionFechas.length; i++)
			matrizNotasReunionFecha[i] = (Nota[]) matrizNotasReunionFechas[i].toArray(new Nota[mayorTamArrayList]);

		return matrizNotasReunionFecha;
	}

	/**
	 * Retorna un arraylist que contiene las notas que estan dentro de las reuniones
	 * que estan dentro de las fechas
	 * 
	 * @param fInicial
	 * @param fFinal
	 * @return
	 */
	private ArrayList<Nota> obtenerArrNotasReunionFecha(LocalDate fInicial, LocalDate fFinal) {
		ArrayList<Reunion> reunionesEnRangoFecha = obtenerReunionesEnRangoFechas(fInicial, fFinal);
		final ArrayList<Nota> notasReunion = new ArrayList<Nota>();
		reunionesEnRangoFecha.stream().forEach(reunion -> notasReunion.addAll(reunion.listarNotas()));
		return notasReunion;
	}

	/**
	 * Elimina los contactos que contengan unos caracteres especificos
	 *
	 * @param caracteres
	 */
	public void eliminarContactosLetras(char... caracteres) {
		eliminarContactosLetras(new StringBuilder().append(caracteres).toString());
	}

	/**
	 * Obtiene una matriz de las reuniones, cada fila representa un mes, el primer
	 * mes es derivado de la fechaInicial y la cantidad de meses (la cantidad de
	 * filas) es indicada por {@code cantRangos}
	 * <li><b> Fila 0 </b>(representa a las reuniones que estén entre la fecha
	 * 01-11-2022 y 30-11-2022)
	 * <li><b> Fila 1 </b>(representa a las reuniones que estén entre la fecha
	 * 01-12-2022 y 31-12-2022)
	 * <li><b> Fila 2 </b>(representa a las reuniones que estén entre la fecha
	 * 01-01-2022 y 30-12-2022)</b></li> <br>
	 * <b>Nota:</b> el ancho de la matriz se ajusta para que no tenga columnas de
	 * nulls sobrantes, pero la matriz en sì puede tener lugares con valores null
	 *
	 * @return la matriz de reuniones con esas especificaciones
	 */
	public Reunion[][] obtenerMatrizReunionesFechas() {
		// crea una fecha cualquiera del mes de noviembre, el metodo
		// obtenerMatrizReunionesFechas ya organiza los días para que den con el
		// resultado
		LocalDate fechaInicial = LocalDate.of(2022, 11, 20);
		// obtiene la matriz de los ultimos 2 meses
		Reunion[][] matrizReunionesFechas = obtenerMatrizReunionesFechasSinRecortar(fechaInicial, 2);
		// prepara la matriz para agregarle una nueva fila
		int pos = matrizReunionesFechas.length;
		matrizReunionesFechas = Arrays.copyOf(matrizReunionesFechas, pos + 1);
		// prepara la matriz para que en la ultima fila quede el arreglo de las
		// reuniones que esten entre el 01/01/2022 y el 30/12/2022
		LocalDate fechaInicialAnio = LocalDate.of(2022, 1, 1);
		LocalDate fechaFinalAnio = LocalDate.of(2022, 12, 30);
		// obtiene las reuniones que esten en el año 2022 sin contar el 31 de diciembre
		ArrayList<Reunion> asa = obtenerReunionesEnRangoFechas(fechaInicialAnio, fechaFinalAnio);
		// las reuniones se agregan a la matriz en la ultima fila
		matrizReunionesFechas[pos] = (Reunion[]) asa.toArray(new Reunion[obtenerCantEspaciosReunionOcupados()]);
		// se recorta el ancho para que quede la misma matriz quitando los valores nulls
		// sobrantes a la derecha
		return recortarMatrizReunionAnchoMayor(matrizReunionesFechas);
	}

	/**
	 * 
	 * Obtiene una matriz de las reuniones, cada fila representa un mes, el primer
	 * mes es derivado de la fechaInicial y la cantidad de meses (la cantidad de
	 * filas) es indicada por {@code cantRangos}, el ancho es la cantidad de
	 * reuniones ocupadas de la agenda
	 *
	 * @param fechaInicial
	 * @param cantRangos
	 * @return
	 */
	public Reunion[][] obtenerMatrizReunionesFechasSinRecortar(LocalDate fechaInicial, int cantRangos) {
		Reunion[][] matrizReunionesSinRec = new Reunion[cantRangos][obtenerCantEspaciosReunionOcupados()];
		for (int i = 0; i < cantRangos; i++) {
			Relacion<LocalDate, LocalDate> minimoMaximo = ProjectUtility.obtenerDiaMinimoMaximo(fechaInicial, i);
			ArrayList<Reunion> asa = obtenerReunionesEnRangoFechas(minimoMaximo.getValor1(), minimoMaximo.getValor2());
			matrizReunionesSinRec[i] = (Reunion[]) asa.toArray(new Reunion[obtenerCantEspaciosReunionOcupados()]);
		}
		return matrizReunionesSinRec;
	}

	/**
	 * Obtiene una matriz de las reuniones, cada fila representa un mes, el primer
	 * mes es derivado de la fechaInicial y la cantidad de meses (la cantidad de
	 * filas) es indicada por {@code cantRangos}
	 *
	 * @param fechaInicial
	 * @param cantRangos
	 * @return
	 */
	public Reunion[][] obtenerMatrizReunionesFechas(LocalDate fechaInicial, int cantRangos) {
		return recortarMatrizReunionAnchoMayor(obtenerMatrizReunionesFechasSinRecortar(fechaInicial, cantRangos));
	}

	/**
	 * Obtiene una matriz de las reuniones, cada fila representa un mes, el primer
	 * mes es derivado de la fechaInicial y la cantidad de meses es determinada por
	 * el mes maximo de las reuniones
	 *
	 * @param fechaInicial
	 * @return
	 */
	public Reunion[][] obtenerMatrizReunionesFechas(LocalDate fechaInicial) {
		Reunion[][] obtenerMatrizReunionesFechas = obtenerMatrizReunionesFechas(fechaInicial,
				obtenerCantEspaciosReunionOcupados());
		return eliminarEspaciosQueTengaTodoNull(obtenerMatrizReunionesFechas);

	}

	/**
	 * Elimina los espacios de la reunion que sean todos null, es util para cuando
	 * no se sabe la altura de la matriz y al final sobran bloques de null
	 *
	 * @param matrizReuniones
	 * @return
	 */
	private Reunion[][] eliminarEspaciosQueTengaTodoNull(Reunion[][] matrizReuniones) {
		int espaciosTodoNull = ProjectUtility.obtenerCantFilasTodoNull(matrizReuniones);
		int tamFinal = matrizReuniones.length - espaciosTodoNull;
		return Arrays.copyOf(matrizReuniones, tamFinal);
	}

	/**
	 * Recorta la matriz de reuniones para que quede con el maximo ancho del que no
	 * sobren nulls
	 *
	 * @param matrizReuniones
	 * @return
	 */
	private Reunion[][] recortarMatrizReunionAnchoMayor(Reunion[][] matrizReuniones) {
		int cantMaxima = ProjectUtility.obtenerAnchoMayorMatriz(matrizReuniones);
		for (int i = 0; i < matrizReuniones.length; i++)
			matrizReuniones[i] = Arrays.copyOf(matrizReuniones[i], cantMaxima);
		return matrizReuniones;
	}

	/**
	 * 
	 * Obtiene las reuniones que estan en un rango de fechas especifico
	 *
	 * @param fechaMenor
	 * @param fechaMayor
	 * @return
	 */
	public ArrayList<Reunion> obtenerReunionesEnRangoFechas(LocalDate fechaMenor, LocalDate fechaMayor) {
		ArrayList<Reunion> listaReunionesRango = new ArrayList<Reunion>();
		for (Reunion reunion : listaReuniones) {
			if (reunion == null)
				continue;
			if (reunion.esEnRangoFecha(fechaMenor, fechaMayor))
				listaReunionesRango.add(reunion);
		}
		return listaReunionesRango;
	}

	/**
	 * Obtiene la cantidad de espacios libres de la reunion
	 *
	 * @return
	 */
	public int obtenerCantEspaciosReunionLibres() {
		return Arrays.stream(listaReuniones).filter(reunion -> reunion == null)
				.collect(Collectors.toCollection(ArrayList::new)).size();
	}

	/**
	 * Obtiene la cantidad de espacios ocupados de la reunion
	 *
	 * @return
	 */
	public int obtenerCantEspaciosReunionOcupados() {
		return listaReuniones.length - obtenerCantEspaciosReunionLibres();
	}

	/**
	 * Elimina los contactos que contengan unos caracteres especificos
	 *
	 * @param caracteres
	 */
	public void eliminarContactosLetras(String caracteres) {
		for (int i = 0; i < listaContactos.length; i++) {
			if (listaContactos[i] == null)
				continue;
			if (listaContactos[i].nombreTieneLetras(caracteres)) {
				listaContactos[i] = null;
			}
		}
	}

	/**
	 * Lista todos los grupos que tengan un tipo especifico y que todos sus
	 * contactos tengan una direccion especifica
	 *
	 * @param categoria
	 * @param direccionContacto
	 * @return
	 * @throws ObjetoNoExisteException en caso de que no exista
	 */
	public ArrayList<Grupo> listarGruposTipoConContactoDireccion(CategoriaGrupo categoria, String direccionContacto)
			throws ObjetoNoExisteException {
		Utility.throwifNull(categoria, "La categoria del grupo no existe");
		Utility.throwifNull(direccionContacto, "La direccon de los contactos no existe");

		return Arrays.stream(listaGrupos)
				.filter(grupo -> grupo != null && grupo.tieneCategoriaDireccionesContacto(categoria, direccionContacto))
				.collect(Collectors.toCollection(ArrayList::new));
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

	/**
	 * Obtiene la lista de contactos que tengan un telefono capicua
	 * 
	 * @return
	 */
	public List<Contacto> obtenerListaContactosTelefonoCapicua() {
		return Arrays.stream(listaContactos).filter(Contacto::tieneNumeroCapicua).collect(Collectors.toList());
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

}
