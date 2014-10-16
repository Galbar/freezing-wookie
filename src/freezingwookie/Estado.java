package freezingwookie;

import IA.Desastres.*;
import java.lang.Integer;
import java.util.ArrayList;

public class Estado {
	
	/**
	 * Coste total recoger a todos los grupos
	 */
	private double costeTotal;

	/**
	 * Coste máximo recoger grupos de prioridad 1
	 */
	private double costeP1;

	/**
	* Centros de emerengia/rescate
	*/
	public Centros centros;

	/**
	* Grupos a rescatar
	*/
	public Grupos grupos;

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
		costeP1 = costeTotal = 0;

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
		helicopteros = new ArrayList<Helicoptero>(e.helicopteros);
		helicopterosCentros = e.helicopterosCentros;
		costeTotal = e.consultaCosteTotal();
		costeP1 = e.consultaCostePrioridad1();
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
		int p = helicopteros.get(h1).getViaje(p1).getNPersonas();
		int cost1 = grupos.get(p1).getNPersonas();
		int cost2 = grupos.get(p2).getNPersonas();
		if (p-cost1+cost2 > 15) return false;
		p = helicopteros.get(h2).getViaje(p2).getNPersonas();
		if (p-cost1+cost2 > 15) return false;
		return true;
	}

	/**
	 * Devuelve el coste del estado
	 * @return coste del estado
	 */
	public double consultaCosteTotal() {
		return costeTotal;
	}

	/**
	 * Devuelve el coste màximo de rescatar al ultimo grupo de prioridad 1
	 * @return coste màximo del rescate del último grupo de prioridad 1
	 */
	public double consultaCostePrioridad1() {
		return costeP1;
	}

	/**
	 * Actualiza el coste del estado
	 */
	public void calculaCoste() {

	}

}
