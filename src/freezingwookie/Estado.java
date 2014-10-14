package freezingwookie;

import IA.Desastres.*;
import java.lang.Integer;
import java.util.ArrayList;

public class Estado {
	/**
	* Lista de grupos asignados a los helicopteros
	*/
	private int[] plan;

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
	public Estado(Centros c, Grupos g, int solIni) {
		centros = c;
		grupos = g;
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
		//if (solIni == 0)
		//	SolucionInicial.distribuido(this);
		//else
		//	SolucionInicial.random(this);
	}

	/**
	 * Constructor de copia
	 * @param  e estado origen
	 */
	public Estado(Estado e) {
		centros = e.centros;
		grupos = e.grupos;
		plan = e.plan.clone();
		helicopteros = new ArrayList<Helicoptero>(e.helicopteros);
		helicopterosCentros = e.helicopterosCentros;
	}

	/**
	 * Devuelve el numero de helicopteros
	 * @return número de helicopteros
	 */
	public int getNHelicopteros() {
		return helicopteros.size();
	}

	/**
	 * Devuelve el helicoptero iessimo del estado
	 * @param  identificador del helicoptero
	 * @return helicoptero iessimo
	 */
	public Helicoptero getHelicoptero(int i) {
		return helicopteros.get(i);
	}

	/**
	 * Devuelve un clon del helicoptero iessimo del estado
	 * @param  identificador del helicoptero
	 * @return clon del helicoptero iessimo
	 */
	public Helicoptero getCopiaHelicoptero(int i) {
		return helicopteros.get(i).clone();
	}

	/**
	 * Asigna un helicoptero
	 * @param posicion de la asignacion
	 * @param helicoptero
	 */
	public void asignarHelicoptero(int i, Helicoptero h) {
		helicopteros.set(i, h);
	}

	/**
	 * Comprueva si el intercambio entre dos grupos asignados a dos helicopteros
	 * @param  Helicptero 1
	 * @param  Identificador del grupo del helicoptero 1
	 * @param  Helicoptero 2
	 * @param  Identificador del grupo del helicoptero 2
	 * @return Retorn true si es posible el intercambio, false en caso contrario
	 */
	public boolean intercambioPosible(int h1, int p1, int h2, int p2) {
		int p = helicopteros.get(h1).getGrupo(p1);
		int cost1 = grupos.get(p).getNPersonas();


		p = helicopteros.get(h2).getGrupo(p2);
		int cost2 = grupos.get(p).getNPersonas();

		// TODO finish this shit *estructura de grupViatges
		return false;
	}
}
