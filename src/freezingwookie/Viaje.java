package freezingwookie;

import java.util.ArrayList;
import java.lang.Integer;

public class Viaje implements Cloneable{
	private ArrayList<Integer> p;
	private int s;

	// TODO: comentar todas estas bonitas funciones

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

	/**
	 * Devuelve el grupo del i del viaje
	 * @param  i posicion del grupo en el viaje
	 * @return   identificador del grupo
	 */
	public int getGrupo(int i) {
		return p.get(i);
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