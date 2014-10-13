package freezingwookie;

import IA.Desastres.*;
import java.util.Random;
import java.io.Console;
import java.lang.Integer;
import java.util.ArrayList;

public class Estado implements Cloneable {
	/**
	* Lista de grupos asignados a los helicopteros
	*/
	private int[] plan;

	/**
	* Coste del estado actual
	*/
	private int costeTotal;

	/**
	 * Coste hasta recoger el último grupo de prioridad 1
	 */
	private int costeP1;

	/**
	* Centros de emerengia/rescate
	*/
	private Centros centros;

	/**
	* Grupos a rescatar
	*/
	private Grupos grupos;

	/**
	 * Estructura contenedora de helicópteros
	 */
	private ArrayList<Helicoptero> helicopteros;
	/**
	 * Estructura que indica dónde empiezan y terminan los helicópteros de cada
	 * centro en helicopteros
	 */
	private ArrayList<Integer> helicopterosCentros;

	/**
	* Constructor
	*/
	public Estado(Centros c, Grupos g) {
		centros = c;
		grupos = g;
		costeTotal = 0;
		costeP1 = 0;
		plan = new int[g.size()];
		
		helicopteros = new ArrayList<Helicoptero>();
		helicopterosCentros = new ArrayList<Integer>();
		int ini = 0;
		for (int i = 0; i < centros.size(); ++i) {
			helicopterosCentros.add(ini);
			Centro centro = centros.get(i);
			for (int j = 0; j < centro.getNHelicopteros(); ++j) {
				helicopteros.add(new Helicoptero());
				++ini;
			}
		}
	}

	/**
	* Devuelve un nuevo Estado copia de este
	*/
	public Estado clone() {
		Estado e = new Estado(centros, grupos);
		e.plan = plan;
		e.costeTotal = costeTotal;
		e.costeP1 = costeP1;
		e.helicopteros = helicopteros;
		return e;
	}

	/**
	* Asigna un grupo a un centro
	*/
	public void asignar(int g, int h) {
		plan[g] = h;
		//coste += 
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
