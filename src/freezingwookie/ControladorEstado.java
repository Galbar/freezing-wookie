package freezingwookie;

import IA.Desastres.*;
import java.io.Console;
import java.lang.Integer;

public class ControladorEstado {

	/**
	* Main
	*/
	public static void main(String[] args) {

		Console console = System.console();

		InputOutput io = new InputOutput();

		Centros cs = new Centros(io.nCentros(), io.nHelicopteros(), io.seed());
		Grupos gs = new Grupos(io.nGrupos(), io.seed());

		Estado e = new Estado(cs, gs, io.initOpt());

		Solucionador sol = new Solucionador();
		Estado resultado;
		if (io.sol() == 0) resultado = new Estado(sol.HillClimbing(e));
		else resultado = new Estado(e);  // Estado(sol.SimulatedAnnealing(e)); // TODO: update this shit

		io.imprimeResultado(e.consultaCosteTotal(), e.consultaCostePrioridad1(),
			resultado.consultaCosteTotal(), resultado.consultaCostePrioridad1());
	}
}