package freezingwookie;

import IA.Desastres.*;
import java.io.Console;
import java.lang.Integer;

public class ControladorEstado {

	/**
	 * Comprueba si la entrada del menu es correcta
	 * @param  i entrada del menu
	 * @return   cierto si la entrada es correcta, falso en caso contrario
	 */
	public static boolean entradaCorrecta(int i) {
		if (i == 1 || i == 0) return true;
		if (i != -101) System.out.println("Entrada incorrecta, vuelva a introducir su opción");
		return false;
	}

	/**
	* Main
	*/
	public static void main(String[] args) {

		Console console = System.console();

		int nc = Integer.parseInt(console.readLine("Introduce el nº centros:"));
		int nh = Integer.parseInt(console.readLine("Introduce el nº helicopteros:"));
		int seed = Integer.parseInt(console.readLine("Introduce el random seed:"));
		Centros cs = new Centros(nc,nh,seed);

		nh = Integer.parseInt(console.readLine("Introduce el nº grupos:"));
		seed = Integer.parseInt(console.readLine("Introduce el random seed:"));
		Grupos gs = new Grupos(nh,seed);

		int initOpt = -101;
		while (!entradaCorrecta(initOpt)) {
			System.out.println("Escoja el método de generación de la solución inicial:");
			System.out.println("  0. Distribuido\n  1. Aleatorio");
			initOpt = Integer.parseInt(console.readLine(">"));
		}

		Estado e = new Estado(cs,gs, initOpt);

		int st = -101;
		while (!entradaCorrecta(st)) {
			System.out.println("Escoja el tipo de solucionador que desea aplicar:");
			System.out.println("  0. Hill climbing\n  1. Simulated annealing");
			st = Integer.parseInt(console.readLine(">"));
		}

		Solucionador sol = new Solucionador();
		Estado resultado;
		if (st == 0) resultado = new Estado(sol.HillClimbing(e));
		else resultado = new Estado(e);  // Estado(sol.SimulatedAnnealing(e)); // TODO: update this shit

		System.out.println("\nResultado:");
		System.out.println("\tTiempo inicial:\n\t\ttodos = "+ e.consultaCosteTotal() + 
			"\n\t\tgrupos prioridad 1 = " + e.consultaCostePrioridad1());
		System.out.println("\tTiempo final:\n\t\ttodos = "+ resultado.consultaCosteTotal() + 
			"\n\t\tgrupos prioridad 1 = " + resultado.consultaCostePrioridad1());
	}
}