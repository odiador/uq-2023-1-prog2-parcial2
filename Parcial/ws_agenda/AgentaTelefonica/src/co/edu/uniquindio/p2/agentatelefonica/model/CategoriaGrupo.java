package co.edu.uniquindio.p2.agentatelefonica.model;

public enum CategoriaGrupo {
	OFICINA("Oficina"), FIESTA("Fiesta"), AMIGOS("Amigos"), FAMILIA("Familia");

	private String nombre;

	private CategoriaGrupo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public static String[] textValues() {
		CategoriaGrupo[] values = CategoriaGrupo.values();
		String[] textValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			textValues[i] = values[i].getNombre();
		return textValues;
	}
}
