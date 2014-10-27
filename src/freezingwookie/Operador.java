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
							int coste1 = eTmp.grupos.get(h1.getGrupo(k)).getNPersonas();
							int coste2 = eTmp.grupos.get(h2.getGrupo(l)).getNPersonas();
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

		// Intercambiar grupos entre los viajes de un helicóptero
		for (int i = 0; i < e.getNHelicopteros(); ++i) {                         // i id del helicoptero
			Helicoptero heli = e.getHelicoptero(i);                              // helicoptero con el que trabajamos
			int hNViajes = heli.getNViajes();
			for (int j = 0; j < hNViajes; ++j) {                                 // j id viaje1
				ArrayList<Integer> viaje1 = heli.getViaje(j);
				for (int k = j+1; k < hNViajes; ++k) {                           // k id viaje2
					ArrayList<Integer> viaje2 = heli.getViaje(k);
					for (int l = 0; l < viaje1.size(); ++l) {                    // l id local (para viaje) de g1
						int g1 = heli.getGrupo(viaje1.get(l));                               // g1 id global (para estado) de g1
						for (int m = 0; m < viaje2.size(); ++m) {                // m id local (para viaje) de g2
							int g2 = heli.getGrupo(viaje2.get(m));                           // g2 id global (para estado) de g2
							if (e.intercambioPosible(i, heli.getPosicionIrreal(viaje1.get(l)),
								i, heli.getPosicionIrreal(viaje2.get(m)))) {
								eTmp = new Estado(e);
								Helicoptero h = eTmp.getCopiaHelicoptero(i);
								int coste1 = eTmp.grupos.get(g1).getNPersonas(); // coste1 Nº personas g1
								int coste2 = eTmp.grupos.get(g2).getNPersonas(); // coste1 Nº personas g2
								h.intercambiarGruposViajes(j, l, coste1, k, m, coste2);
								eTmp.asignarHelicoptero(i, h);
								ret.add(new Successor("Helicóptero "
									+ String.valueOf(i) + ": grupo "
									+ String.valueOf(g1)
									+ " ahora está en el viaje " + String.valueOf(j)
									+ "; grupo "
									+ String.valueOf(g2)
									+ " ahora está en el viaje " + String.valueOf(k)
									+ ".\n",
									eTmp));
							}
						}
					}
				}
			}
		}
		
		// Intercambiar orden de los viajes de un helicóptero
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

		// Intercambiar del orden del los grupos de un viaje de un helicioptero
		for (int i = 0; i < e.getNHelicopteros(); ++i) {
			Helicoptero h = e.getCopiaHelicoptero(i);
			for (int j = 0; j < h.getNViajes(); ++j) {
				for (int k = 0; k < h.getNGruposViaje(j); ++k) {
					for (int p = k; p < h.getNGruposViaje(j); ++p) {
						h.intercambiarOrdenGrupos(j, k, p);
						eTmp = new Estado(e);
						eTmp.asignarHelicoptero(i, h);
						ret.add(new Successor("Helicóptero "
							+ String.valueOf(i) + " modifica el orden de los grupos en el viaje "
							+ String.valueOf(k) + " el grupo en posicion " + String.valueOf(k)
							+ " se intercambia con el grupo en posicion " + String.valueOf(p) + ".\n",
							eTmp));
					}
				}
			}
		}

		return ret;
	}
}
