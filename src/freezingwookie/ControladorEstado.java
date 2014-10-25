package freezingwookie;

import IA.Desastres.*;
import java.io.Console;
import java.lang.Integer;
import java.lang.Float;

public class ControladorEstado {

	/**
	* Main
	*/
	public static void main(String[] args) {

		Console console = System.console();

		InputOutput io = new InputOutput();

		int seed = io.seed();

		Centros cs = new Centros(io.nCentros(), io.nHelicopteros(),seed);
		Grupos gs = new Grupos(io.nGrupos(), seed);

		Estado e = new Estado(cs, gs, io.initOpt(), seed);

		Solucionador sol = new Solucionador();
		Estado resultado;

		if (io.sol() == 0) resultado = new Estado(sol.HillClimbing(e));
		else {
			resultado = new Estado(sol.SimulatedAnnealing(e, io.getIteraciones(), io.getTemperatura(), 
				io.getK(), io.getLambda()));
		}
		io.imprimeResultado(e.consultaCosteTotal(), e.consultaCostePrioridad1(),
			resultado.consultaCosteTotal(), resultado.consultaCostePrioridad1());
	}
}