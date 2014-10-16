package freezingwookie;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;

public class Solucionador {

	public Estado HillClimbing(Estado estadoInicial) {
        try {
            Problem problema = new Problem(estadoInicial,new Operador(),new Final(),new FuncionHeuristica());
            HillClimbingSearch busqueda =  new HillClimbingSearch();
            SearchAgent agente = new SearchAgent(problema,busqueda);

            return (Estado)busqueda.getGoalState();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
            return estadoInicial;
        }
    }

    // NOTE: NO ADAPTADA AL NOSTRE PROBLEMA
    public Estado SimulatedAnnealing(Estado estadoInicial, int iteracion, int grados, int K, float B) {
        try {
            Problem problema = new Problem(estadoInicial,new Operador(),new Final(),new FuncionHeuristica());
            SimulatedAnnealingSearch busqueda =  new SimulatedAnnealingSearch(iteracion,grados,K,B);
            SearchAgent agente = new SearchAgent(problema,busqueda);

            return (Estado)busqueda.getGoalState();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
            return estadoInicial;
        }
    }
}