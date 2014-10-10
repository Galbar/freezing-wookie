package freezingwookie;

import IA.Desastres.*;
import java.util.Random;
import java.io.Console;
import java.lang.Integer;

public class Estado {
	/**
	* Lista de grupos asignados a los centros
	*/
	private int[] plan;

	/**
	* Coste del estado actual
	*/
	private int coste;

	/**
	* Centros de emerengia/rescate
	*/
	Centros centros;

	/**
	* Grupos a rescatar
	*/
	Grupos grupos;

	/**
	* Constructor
	*/
	public Estado(Centros centros, Grupos grupos) {
		Console console = System.console();
		this.centros = centros;
		this.grupos = grupos;
		coste = 0;
		plan = new int[ng];
	}

	/**
	* Asigna un grupo a un centro
	*/
	public void asignar(int g, int c) {
		// TODO: implementar void asignar(int g, int c)
	} 

	/**
	* Intercambia los centros asignados a dos grupos
	*/
	public void intercambia(int g1, int g2) {
		// TODO: implementar void intercambia(int g1, int g2)
	}

	/**
	 * @brief Desasignar un grupo de un centro
	 * @details Desasigna un grupo de un centro y actualiza costes
	 * 
	 * @param g ID grupo
	 * @param c ID centro
	 */
	public void borrar(int g, int c) {
		// TODO: implementar void borrar(int g, int c)
	}

	/**
	* Calcula una primera solucion con una distribucion no optima
	*/
	public void primeraSol() {
		// TODO: implementar void primeraSol()
	}

	/**
	* Mejora la solucion actual
	*/
	public void mejora() {
		// TODO: implementar void mejora()
	}

	/**
	* Imprimir estado
	*/
	public void pintaEstado() {
		// TODO: implementar void pintaEstado()
	}
}
