package freezingwookie;

import java.util.ArrayList;
import java.lang.Integer;

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

	public int size() {
		return p.size();
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
		for (int i = 0; i < viajes.size(); ++i) {
			h.viajes.add(viajes.get(i).clone());
		}
		h.gruposViajes = new ArrayList<Integer>(gruposViajes);
		return h;
	}

	/**
	 * Añade un nuevo grupo a los grupos asignados del helicóptero
	 * @param g ID grupo asignado
	 */
	public void asignarGrupo(int g, int s) {
		if (viajes.get(viajes.size()-1).size() >= 2 ||
			viajes.get(viajes.size()-1).getNPersonas() + s > 15) {
			viajes.add(new Viaje());
		}
		viajes.get(viajes.size()-1).add(g, s);
		gruposAsignados.add(g);
		gruposViajes.add(viajes.size()-1);
	}

	/**
	 * Pone en la posición p de gruposAsignados el grupo g
	 * @param g ID grupo
	 * @param p Posición en la lista (0 <= p < getNGrupos())
	 * @param s quantitat de persones del grup assignat
	 */																			// NOTE: not used
	public void asignarGrupo(int g, int p, int s) {
		if (viajes.get(viajes.size()-1).size() >= 2 ||
			viajes.get(viajes.size()-1).getNPersonas() + s > 15) {
			viajes.add(new Viaje());
		}
		viajes.get(viajes.size()-1).add(g, s);
		gruposAsignados.set(p, g);
		gruposViajes.add(viajes.size()-1);
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
	 * Dado un grupo devuelv en que viaje esta asingado
	 * @param  p1 id grupo
	 * @return    viaje que contiene p1
	 */
	public Viaje getViaje(int p1) {
		int i = gruposViajes.get(p1);
		return viajes.get(i);
	}	

	/**
	 * Intercambia los grupos en posición p1 de this.gruposAsignados y p2 de 
	 * h.gruposAsignados
	 * @param h  helicóptero con quien intercambiar
	 * @param p1 posición del grupo en this.gruposAsignados
	 * @param p2 posición del grupo en h.gruposAsignados
	 */
	public void intercambiarGrupos(int p1, int coste1, Helicoptero h, int coste2, int p2) {
		int g1 = gruposAsignados.get(p1);
		int g2 = h.gruposAsignados.get(p2);
		viajes.get(gruposViajes.get(p1)).update(coste1,coste2);
		h.viajes.get(h.gruposViajes.get(p2)).update(coste2,coste1);
		gruposAsignados.set(p1,g2);
		h.gruposAsignados.set(p2,g1);
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

}