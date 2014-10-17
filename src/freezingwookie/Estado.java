package freezingwookie;

import IA.Desastres.*;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

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
		if (solIni == 0) solucionInicialDistribuido();
		else solucionInicialRandom();
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
		double tmp1, tmp2;
		costeTotal = costeP1 = -1;
		for (int i = 0; i < helicopteros.size(); ++i) {
			tmp2 = tmp1 = 0;
			Helicoptero h = helicopteros.get(i);
			int it = 0;
			for (int k = 0; k < helicopterosCentros.size(); ++k) {
				if (helicopterosCentros.get(k) <= i && centros.get(k).getNHelicopteros()+
					helicopterosCentros.get(k) >= i) {
					it = k;
					break;
				}
			}
			int x = centros.get(it).getCoordX();
			int y = centros.get(it).getCoordY();
			for (int j = 0; j < h.getNViajes(); ++j) {
				Viaje viaje = h.getViaje(j);
				tmp1 += getCosteViaje(viaje,x,y);
				if (contineGrupoP1(viaje)) tmp2 = tmp1;
			}
			if (costeTotal == -1) {
				costeTotal = tmp1;
				costeP1 = tmp2;
			}
			else {
				costeTotal += tmp1;
				if (costeP1 < tmp2) costeP1 = tmp2;
			}
		}
	}

	/**
	 * Calcula el coste de un viaje
	 * @param  v identificador del viaje
	 * @return   coste del viaje
	 */
	public double getCosteViaje(Viaje viaje, int x, int y) {
		double cost = 0;
		int oldx = x;
		int oldy = y;
		int newx, newy;
		int mult;
		for (int i = 0; i < viaje.size(); ++i) {
			if (grupos.get(viaje.getGrupo(i)).getPrioridad() == 1) mult = 2;
			else mult = 1;
			newx = centros.get(helicopterosCentros.get(i)).getCoordX();
			newy = centros.get(helicopterosCentros.get(i)).getCoordY();
			cost += (grupos.get(viaje.getGrupo(i)).getNPersonas()*mult+distancia(newx,newy,oldx,oldy)*(100/60));
			oldx = newx;
			oldy = newy; 
		}
		return cost+distancia(x,y,oldx,oldy)*(100/60);
	}

	private double distancia(int newx, int newy, int oldx, int oldy) {
		return Math.sqrt(Math.pow(newx-oldx,2)+Math.pow(newy-oldy,2));
	}

	/**
	 * Comprueba si el viaje rescata grupos de prioridad 1
	 * @param  v identificador del viaje
	 * @return   devuelve si el viaje rescata a grupos de prioridad 1
	 */
	public boolean contineGrupoP1(Viaje viaje) {
		for (int i = 0; i < viaje.size(); ++i) {
			if (grupos.get(viaje.getGrupo(i)).getPrioridad() == 1) return true;
		}
		return false;
	}

	/**
     * Asigna al estado actual una solucion inicial random
     */
    public void solucionInicialRandom() {
    	Random myRandom = new Random();
        for (int i = 0; i < grupos.size(); ++i) {
            int tmp = myRandom.nextInt(helicopterosCentros.size());
            helicopteros.get(helicopterosCentros.get(tmp)+myRandom.nextInt(centros.get(tmp).
            	getNHelicopteros())).asignarGrupo(i,grupos.get(i).getNPersonas());
        }
    }

    public void solucionInicialDistribuido() {
    	// TODO: everything
    }

}
