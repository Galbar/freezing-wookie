package freezingwookie;

import be.humphreys.simplevoronoi.Voronoi;
import be.humphreys.simplevoronoi.GraphEdge;
import cc.alessio.geometry2D.*;
import java.lang.Double;
import IA.Desastres.*;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
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
			Centro centro = centros.get(i);
			for (int j = 0; j < centro.getNHelicopteros(); ++j) {
				helicopterosCentros.add(ini);
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
    	Voronoi voronoi = new Voronoi(1);
    	double[] in_x = new double[centros.size()];
		double[] in_y = new double[centros.size()];
		for (int i = 0; i < centros.size(); ++i) {
			in_x[i] = centros.get(i).getCoordX();
			in_y[i] = centros.get(i).getCoordY();
		}
		// System.out.println("Resultado de voronoi");
		List<GraphEdge> list = voronoi.generateVoronoi(in_x, in_y, 0, 50, 0, 50);
		// System.out.println("Inicializar areas");
		ArrayList<Polygon> areas = new ArrayList<Polygon>();
		for (int i = 0; i < centros.size(); ++i) {
			areas.add(new Polygon(1));
		}
		// System.out.println("Dar limites a las areas");
		for (int i = 0; i < list.size(); ++i) {
			GraphEdge ge = list.get(i);
			areas.get(ge.site1).add(ge.x1, ge.y1, ge.x2, ge.y2);
			areas.get(ge.site2).add(ge.x1, ge.y1, ge.x2, ge.y2);
		}
		// System.out.println("Cerrar areas abiertas (areas pegadas al borde)");
		for (int i = 0; i < areas.size(); ++i) {
			Polygon area = areas.get(i);
			if (area.size() != area.vertexSize()) {
				if (area.getMinBound().y == 0)
					area.add(new Point(area.getMinBound().x, 0), new Point(area.getMaxBound().x, 0));
				if (area.getMaxBound().y == 10)
					area.add(new Point(area.getMinBound().x, 10), new Point(area.getMaxBound().x, 10));
				if (area.getMinBound().x == 0)
					area.add(new Point(0, area.getMinBound().y), new Point(0, area.getMaxBound().y));
				if (area.getMaxBound().x == 10)
					area.add(new Point(10, area.getMinBound().y), new Point(10, area.getMaxBound().y));
			}
		}
		// System.out.println("Asignar a centros los grupos que estén en su area");
    	Random random = new Random();
		for (int i = 0; i < grupos.size(); ++i) {
			Grupo grupo = grupos.get(i);
			Point p = new Point(grupo.getCoordX(), grupo.getCoordY());
			boolean found = false;
			for (int j = 0; j < areas.size() && !found; ++j) {
				Polygon area = areas.get(i);
				if (area.isContained(p)) {
					Centro centro =  centros.get(j);
					int h = random.nextInt(centro.getNHelicopteros());
					Helicoptero heli = helicopteros.get(
						helicopterosCentros.get(centro.getNHelicopteros() + h)
						);
					heli.asignarGrupo(i, grupo.getNPersonas());
					found = true;
				}
			}
		}
    }

}
