package co.edu.uniquindio.p2.agentatelefonica.model;

public enum CategoriaNota {
	PRIVATA("Privada"), PUBLICA("Publica");

	private String nombre;

	private CategoriaNota(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene el arreglo de los valores de los nombres de la categoria de la nota
	 *
	 * @return
	 */
	public static String[] textValues() {
		CategoriaNota[] values = CategoriaNota.values();
		String[] textValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			textValues[i] = values[i].getNombre();
		return textValues;
	}

	/**
	 * Obtiene la categoria de la nota a partir de su nombre, si no se encuentra se
	 * retorna un null
	 *
	 * @param nombre
	 * @return
	 */
	public static CategoriaNota obtenerCategoriaDeNombre(String nombre) {
		CategoriaNota[] values = CategoriaNota.values();
		for (CategoriaNota categoria : values)
			if (categoria.getNombre().equals(nombre))
				return categoria;
		return null;
	}
}
