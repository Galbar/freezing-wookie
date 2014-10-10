package freezingwookie;

import IA.Desastres.*;
import java.lang.System;

public class Estado implements Cloneable {
	/**
	* Lista de grupos asignados a los centros
	*/
	private int[] plan;

	/**
	 * Lista de referencias a centros. Cada posición representa un helicoptero
	 */
	private int[] helicopteros;

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
	public Estado(Centros c, Grupos g) {
		centros = c;
		grupos = g;
		coste = 0;
		plan = new int[g.size()];
		int sum = 0;
		for (int i = 0; i < c.size(); i++) {
			sum += c.get(i).getNHelicopteros();
		}

		helicopteros = new int[sum];
		for (int i = 0; i < c.size(); i++) {
			for (int j = 0; j < c.get(i).getNHelicopteros(); j++) {
				helicopteros[i*c.size()+j] = i;
			}
		}
	}

	/**
	* Devuelve un nuevo Estado copia de este
	*/
	public Estado clone() {
		Estado e = new Estado(centros, grupos);
		e.plan = plan;
		e.coste = coste;
		return e;
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
