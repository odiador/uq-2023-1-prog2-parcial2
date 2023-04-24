package co.edu.uniquindio.p2.agentatelefonica.tests;

import co.edu.uniquindio.p2.agentatelefonica.exceptions.ArregloLlenoException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ObjetoNoExisteException;
import co.edu.uniquindio.p2.agentatelefonica.exceptions.ReunionException;
import co.edu.uniquindio.p2.agentatelefonica.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Test;

public class Test1 {
	@Test
	public void testPunto4() {
		Agenda a = new Agenda("asda", 10, 10, 10);

		for (int i = 0; i < 10; i++)
			try {
				char caracter = (char) (i + 'a');
				String nombre = "" + caracter;
				Reunion reunion = new Reunion(nombre, "asdad", LocalDate.now().plusDays(i * 10), LocalTime.now());
				a.agregarReunion(reunion);
			} catch (ObjetoNoExisteException | ReunionException | ArregloLlenoException e) {
				System.err.println(e.getMessage());
			}
		Reunion[][] matrizReunionesFechas = a.obtenerMatrizReunionesFechas(LocalDate.now());
		System.out.println(matrizReunionesFechas.length);
		for (int i = 0; i < matrizReunionesFechas.length; i++) {
			System.out.println(Arrays.toString(matrizReunionesFechas[i]));
		}
	}

}
