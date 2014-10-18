package freezingwookie;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

public class Operador implements SuccessorFunction{

	/**
	 * Genera una lista de todos los posibles estados vecinos viables del actual
	 * @param  eActual estado actual
	 * @return         lista de los vecinos al estado actual
	 */
	public List getSuccessors(Object eActual) {
		ArrayList ret = new ArrayList();
		
		Estado e = (Estado)eActual;
		Estado eTmp;
		
		// Intercambiar grupos de helicópteros
		for (int i = 0; i < e.getNHelicopteros(); ++i) {
			int h1NGrupos = e.getHelicoptero(i).getNGrupos();
			for (int j = i+1; j < e.getNHelicopteros(); ++j) {
				int h2NGrupos = e.getHelicoptero(j).getNGrupos();
				for (int k = 0; k < h1NGrupos; ++k)
					for (int l = 0; l < h2NGrupos; ++l) {
						if (e.intercambioPosible(i, k, j, l)) {
							eTmp = new Estado(e);
							Helicoptero h1 = eTmp.getCopiaHelicoptero(i);
							Helicoptero h2 = eTmp.getCopiaHelicoptero(j);
							int coste1 = eTmp.grupos.get(k).getNPersonas();
							int coste2 = eTmp.grupos.get(l).getNPersonas();
							h1.intercambiarGrupos(k, coste1, h2, coste2, l);
							eTmp.asignarHelicoptero(i, h1);
							eTmp.asignarHelicoptero(j, h2);
							ret.add(new Successor("Helicóptero "
								+ String.valueOf(i) + " ahora rescata al grupo "
								+ String.valueOf(h1.getGrupo(k))
								+ ". Helicóptero " + String.valueOf(j)
								+ String.valueOf(i) + " ahora rescata al grupo "
								+ String.valueOf(h2.getGrupo(l)) + ".\n",
								eTmp));
						}
					}
			}
		}

		// Asignar un grupo de un helicóptero a otro
		for (int i = 0; i < e.getNHelicopteros(); ++i) {
			int h1NGrupos = e.getHelicoptero(i).getNGrupos();
			for (int j = i+1; j < e.getNHelicopteros(); ++j) {    // Helicópteros todos con todos
				int h2NGrupos = e.getHelicoptero(j).getNGrupos();
				for (int k = 0; k < h1NGrupos; ++k) {             // para todos sus grupos todos con todos
					eTmp = new Estado(e);
					Helicoptero h1 = eTmp.getCopiaHelicoptero(i);
					Helicoptero h2 = eTmp.getCopiaHelicoptero(j);
					int coste = eTmp.grupos.get(k).getNPersonas();
					int idGrupo = h1.getGrupo(k);
					h1.desasignarGrupo(k, coste);
					h2.asignarGrupo(idGrupo, coste);
					eTmp.asignarHelicoptero(i, h1);
					eTmp.asignarHelicoptero(j, h2);
					ret.add(new Successor("Helicóptero "
						+ String.valueOf(i) + " ahora no rescata al grupo "
						+ String.valueOf(idGrupo)
						+ ". Helicóptero " + String.valueOf(j)
						+ String.valueOf(i) + " ahora rescata al grupo "
						+ String.valueOf(idGrupo) + ".\n",
						eTmp));
				}
			}
		}

		// TODO: Intercambiar grupos entre los viajes de un helicóptero
		// TODO: Intercambiar orden de grupos dentro de un viaje de un helicóptero
		
		// TODO: Intercambiar orden de los viajes de un helicóptero
		for (int i = 0; i < e.getNHelicopteros(); ++i) {
			int hNViajes = e.getHelicoptero(i).getNViajes();
			for (int j = 0; j < hNViajes; ++j) {
				for (int k = j+1; k < hNViajes; ++k) {            // para todos sus viajes, todos con todos
					eTmp = new Estado(e);
					Helicoptero h = eTmp.getCopiaHelicoptero(i);
					h.intercambiarViajes(j, k);
					eTmp.asignarHelicoptero(i, h);
					ret.add(new Successor("Helicóptero "
						+ String.valueOf(i) + " hace primero el viaje "
						+ String.valueOf(k)
						+ " que el viaje " + String.valueOf(j)
						+ String.valueOf(i) + ".\n",
						eTmp));
				}
			}
		}

		return ret;
	}
}
