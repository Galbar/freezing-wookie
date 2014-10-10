package freezingwookie;

import java.util.ArrayList;
import java.lang.Integer;

class Viaje {
	private ArrayList<Integer> p;
	public void swap(int i, int j) {
		Integer tmp = p.get(i);
		p.set(i, p.get(j));
		p.set(j, tmp);
	}
}

public class Helicoptero implements Cloneable {
	private ArrayList<Integer> gruposAsignados;
	private ArrayList<Viaje> viajes;

	/**
	 * Constructora
	 */
	public Helicoptero() {
		gruposAsignados = new ArrayList<Integer>();
		viajes = new ArrayList<Viaje>();
	}

	/**
	 * Añade un nuevo grupo a los grupos asignados del helicóptero
	 * @param g ID grupo asignado
	 */
	public void asignarGrupo(int g) {
		gruposAsignados.add(g);
	}

	/**
	 * Pone en la posición p de gruposAsignados el grupo g
	 * @param g ID grupo
	 * @param p Posición en la lista (0 <= p < getNGrupos())
	 */
	public void asignarGrupo(int g, int p) {
		gruposAsignados.set(p, g);
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
	 * Devuelve el número de grupos que tiene asignado el helicóptero
	 * @return Número de grupos asignados
	 */
	public int getNGrupos() {
		return gruposAsignados.size();
	}

}