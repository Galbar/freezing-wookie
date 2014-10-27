package freezingwookie;

import java.util.ArrayList;

class Viaje implements Cloneable{
	private ArrayList<Integer> p;
	private int s;

	public Viaje() {
		p = new ArrayList<Integer>();
		s = 0;
	}

	public void add(int i, int s) {
		p.add(new Integer(i));
		this.s += s;
	}

	public void update(int i, int j) {
		s += j;
		s -= i;
	}

	public int getGrupo(int i) {
		return p.get(i);
	} 

	public int size() {
		return p.size();
	}

	public void remove (int i, int s) {
		for (int k = 0; k < p.size(); ++k) {
			if (p.get(k).equals(i)) {
				p.remove(k);
				break;
			}
		}
		this.s -= s;
	}

	public Viaje clone() {
		Viaje v = new Viaje();
		v.p = new ArrayList<Integer>(p);
		v.s = s;
		return v;
	} 

	public int getNPersonas() {
		return s;
	}
	
	public void swap(int i, int j) {
		Integer tmp = p.get(i);
		p.set(i, p.get(j));
		p.set(j, tmp);
	}
}

public class Helicoptero implements Cloneable {
	private ArrayList<Integer> gruposAsignados;
	private ArrayList<Viaje> viajes;
	private ArrayList<Integer> gruposViajes;

	/**
	 * Constructora
	 */
	public Helicoptero() {
		gruposAsignados = new ArrayList<Integer>();
		viajes = new ArrayList<Viaje>();
		gruposViajes = new ArrayList<Integer>();
	}

	/**
	 * Clona un helicoptero
	 */
	public Helicoptero clone() {
		Helicoptero h = new Helicoptero();
		h.gruposAsignados = new ArrayList<Integer>(gruposAsignados);
		h.viajes = new ArrayList<Viaje>();
		for (int i = 0; i < viajes.size(); ++i) {
			h.viajes.add(viajes.get(i).clone());
		}
		h.gruposViajes = new ArrayList<Integer>(gruposViajes);
		return h;
	}

	/**
	 * Añade un nuevo grupo a los grupos asignados del helicóptero
	 * @param g ID grupo asignado
	 * @param s numero de personas en el grupo
	 */
	public void asignarGrupo(int g, int s) {
		if (viajes.size() > 0) {
			if (viajes.get(viajes.size()-1).size() >= 2 ||
				viajes.get(viajes.size()-1).getNPersonas() + s > 15) {
				viajes.add(new Viaje());
			}
			Viaje viaje = viajes.get(viajes.size()-1);
			gruposAsignados.add(g);
			viaje.add(getNGrupos()-1, s);
			gruposViajes.add(viajes.size()-1);
		} else {
			Viaje tmp = new Viaje();
			gruposAsignados.add(g);
			tmp.add(getNGrupos()-1,s);
			viajes.add(tmp);
			gruposViajes.add(viajes.size()-1);
		}
	}

	/**
	 * Devuelve el ID del grupo asignado que está en la posición p de 
	 * gruposAsignados
	 * @param  p Posición en la lista
	 * @return   ID del grupo p (0 <= p < getNGrupos())
	 */
	public int getGrupo(int p) {
		return gruposAsignados.get(p);
	}

	/**
	 * Intercambia los grupos en posición p1 de this.gruposAsignados y p2 de 
	 * h.gruposAsignados
	 * @param h  helicóptero con quien intercambiar
	 * @param p1 posición del grupo en this.gruposAsignados
	 * @param p2 posición del grupo en h.gruposAsignados
	 */
	
	public void intercambiarGrupos(int p1, int coste1, Helicoptero h,
								   int coste2, int p2) {
		int g1 = gruposAsignados.get(p1);
		int g2 = h.gruposAsignados.get(p2);
		viajes.get(gruposViajes.get(p1)).update(coste1,coste2);
		h.viajes.get(h.gruposViajes.get(p2)).update(coste2,coste1);
		gruposAsignados.set(p1,g2);
		h.gruposAsignados.set(p2,g1);
	}

	public void intercambiarViajes(int v1, int v2) {
		Viaje tmp = viajes.get(v1);
		viajes.set(v1, viajes.get(v2));
		viajes.set(v2, tmp);
	}

	/**
	 * Intercambia el grupo p1 del viaje v1 por el grupo p2 del viaje v2
	 * y viceversa
	 * @param p1 posicion de grupo en viaje v1
	 * @param coste1 numero de personas en p1
	 * @param p2 posicion de grupo en v2
	 * @param coste2 numero de personas en p2
	 */
	public void intercambiarGruposViajes(int v1, int p1, int coste1,
								    	 int v2, int p2, int coste2) {
		p1 = viajes.get(v1).getGrupo(p1);
		p2 = viajes.get(v2).getGrupo(p2);
		int g1 = gruposAsignados.get(p1);
		int g2 = gruposAsignados.get(p2);
		viajes.get(gruposViajes.get(p1)).update(coste1,coste2);
		viajes.get(gruposViajes.get(p2)).update(coste2,coste1);
		gruposAsignados.set(p1,g2);
		gruposAsignados.set(p2,g1);
	}

	/**
	 * Devuelve el número de grupos que tiene asignado el helicóptero
	 * @return Número de grupos asignados
	 */
	public int getNGrupos() {
		return gruposAsignados.size();
	}

	/**
	 * Devuelve el número de viajes que tiene asignado el helicóptero
	 * @return Número de viajes asignados
	 */
	public int getNViajes() {
		return viajes.size();
	}

	/**
	 * Devuelve el numero de grupos que el helicóptero visita en el viaje <em>v</em>
	 * @param  v identificador de viaje
	 * @return   numero de grupos en el viaje
	 */
	public int getNGruposViaje(int v) {
		return viajes.get(v).size();
	}

	/**
	 * Devuelve el numero de grupos que el helicóptero visita en el viaje <em>v</em>
	 * @param  v identificador de viaje
	 * @return   numero de grupos en el viaje
	 */
	public int getNPersonasViaje(int v) {
		return viajes.get(v).getNPersonas();
	}

	/**
	 * Devuelve el numero de grupos que el helicóptero visita en el viaje <em>v</em>
	 * @param  v identificador de viaje
	 * @return   numero de grupos en el viaje
	 */
	public ArrayList<Integer> getGruposViaje(int v) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Viaje viaje = viajes.get(v);
		for (int i = 0; i < viaje.size(); ++i) {
			ret.add(gruposAsignados.get(viaje.getGrupo(i)));
		}
		return ret;
	}

	public ArrayList<Integer> getViaje(int v) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		Viaje viaje = viajes.get(v);
		for (int i = 0; i < viaje.size(); ++i) {
			ret.add(viaje.getGrupo(i));
		}
		return ret;
	}

	/**
	 * Devuleve el identificador del viaje en el que está el grupo
	 * con posición p1 en gruposAsignados
	 * @param p1 posición del grupo en gruposAsignados
	 * @return   identificador del viaje
	 */
	public int getViajeGrupo(int p1) {
		return gruposViajes.get(p1);
	}

	/**
	 * Intercambia el orden de los grupos en un viaje
	 * @param k identificador del grupo
	 * @param o posicion del primero grupo a intercambiar
	 * @param p posicion del segundo grupo a intercambiar
	 */
	public void intercambiarOrdenGrupos(int k, int o, int p) {
		viajes.get(k).swap(o, p);
	}

	public int getPosicionIrreal(int p) {
		int count = 0;
		return p;
	}
}