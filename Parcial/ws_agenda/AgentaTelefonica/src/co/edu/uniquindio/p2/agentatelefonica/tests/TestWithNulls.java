package co.edu.uniquindio.p2.agentatelefonica.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

import co.edu.uniquindio.p2.agentatelefonica.model.Contacto;

public class TestWithNulls {
	@Test
	public void testContactoListaConNulls() {
		Contacto c = new Contacto("robinson", "asd");
		Contacto c2 = new Contacto("as", "asd");
		Contacto contactos[] = { c, c2, c2, null, c, null, c };
		ArrayList<Contacto> arrayListContactos = Arrays.asList(contactos).stream()
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(arrayListContactos);
		arrayListContactos = arrayListContactos.stream()
				.filter(contacto -> contacto != null && contacto.getNombre().equals("robinson"))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(arrayListContactos);
		System.out.println(arrayListContactos.indexOf(null));
	}

	@Test
	public void testContactoListaSinNulls() {
		Contacto c = new Contacto("asd", "asd");
		Contacto contactos[] = { c, c, c, c, c, c, c };
		ArrayList<Contacto> arrayListContactos = Arrays.asList(contactos).stream()
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(arrayListContactos);
		System.out.println(arrayListContactos.indexOf(null));
	}
}
