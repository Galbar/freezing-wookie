package freezingwookie;

import aima.search.framework.HeuristicFunction;
import java.lang.Math;

public class FuncionHeuristica  implements HeuristicFunction{

    public double getHeuristicValue(Object estado) {
        Estado e = (Estado)estado;
        e.calculaCoste();
        double c1 = e.consultaCosteTotal();
        double c2 = e.consultaCostePrioridad1();
        double ret = Math.sqrt(c1)+Math.pow(c2,2);
        return ret;
    }
}