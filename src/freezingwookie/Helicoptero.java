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
	private ArrayList<Integer> gruposInvalidos;

	private int getPosicionReal(int p) {
		int count = 0;
		for (int i = 0; i < gruposInvalidos.size(); ++i)
			if (gruposInvalidos.get(i) < p) count++;
		return p+count;
	}

	/**
	 * Constructora
	 */
	public Helicoptero() {
		gruposAsignados = new ArrayList<Integer>();
		viajes = new ArrayList<Viaje>();
		gruposViajes = new ArrayList<Integer>();
		gruposInvalidos = new ArrayList<Integer>();
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
		h.gruposInvalidos = new ArrayList<Integer>(gruposInvalidos);
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
			if (gruposInvalidos.size() > 0) {
				int i = gruposInvalidos.get(0);
				gruposInvalidos.remove(0);
				gruposAsignados.set(i, g);
				viaje.add(i, s);
				gruposViajes.set(i, viajes.size()-1);
			}
			else {
				gruposAsignados.add(g);
				viaje.add(getNGrupos()-1, s);
				gruposViajes.add(viajes.size()-1);
			}
		} else {
			Viaje tmp = new Viaje();
			gruposAsignados.add(g);
			tmp.add(getNGrupos()-1,s);
			viajes.add(tmp);
			gruposViajes.add(viajes.size()-1);
		}
	}

	/**
	 * Quita un grupo de los grupos asignados del helicóptero
	 * @param p Posición en la lista
	 * @param s numero de personas en el grupo
	 */
	public void desasignarGrupo(int p, int s) {
		p = getPosicionReal(p);
		gruposInvalidos.add(p);
		Viaje viaje = viajes.get(gruposViajes.get(p));
		viaje.remove(p, s);
	}

	/**
	 * Devuelve el ID del grupo asignado que está en la posición p de 
	 * gruposAsignados
	 * @param  p Posición en la lista
	 * @return   ID del grupo p (0 <= p < getNGrupos())
	 */
	public int getGrupo(int p) {
		p = getPosicionReal(p);
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
		p1 = getPosicionReal(p1);
		p2 = h.getPosicionReal(p2);
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
	 * Devuelve el número de grupos que tiene asignado el helicóptero
	 * @return Número de grupos asignados
	 */
	public int getNGrupos() {
		return gruposAsignados.size() - gruposInvalidos.size();
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
			ret.add(gruposAsignados.get(
				viaje.getGrupo(i)));
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
		p1 = getPosicionReal(p1);
		return gruposViajes.get(p1);
	}

}