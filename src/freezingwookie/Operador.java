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

		// TODO: "Intercambiar" helicopteros entre centros
		// TODO: Intercambiar grupos entre los viajes de un helicóptero
		// TODO: Intercambiar orden de grupos dentro de un viaje de un helicóptero
		// TODO: Intercambiar orden de los viajes de un helicóptero

		return ret;
	}
}
