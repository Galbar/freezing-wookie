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

		Centros cs = new Centros(io.nCentros(), io.nHelicopteros(), io.seed());
		Grupos gs = new Grupos(io.nGrupos(), io.seed());

		Estado e = new Estado(cs, gs, io.initOpt());

		Solucionador sol = new Solucionador();
		Estado resultado;

		if (io.sol() == 0) resultado = new Estado(sol.HillClimbing(e));
		else {
			int iteracion   = Integer.parseInt(console.readLine("iteracion: "));
			int temperatura = Integer.parseInt(console.readLine("temperatura: "));
			int K           = Integer.parseInt(console.readLine("K: "));
			float lambda    = Float.parseFloat(console.readLine("lambda: "));
			resultado = new Estado(sol.SimulatedAnnealing(e, iteracion, temperatura, K, lambda));
		}

		io.imprimeResultado(e.consultaCosteTotal(), e.consultaCostePrioridad1(),
			resultado.consultaCosteTotal(), resultado.consultaCostePrioridad1());
	}
}