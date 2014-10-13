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

		int nc = Integer.parseInt(console.readLine("Introduce el nº centros:"));
		int nh = Integer.parseInt(console.readLine("Introduce el nº helicopteros:"));
		int seed = Integer.parseInt(console.readLine("Introduce el random seed:"));
		Centros cs = new Centros(nc,nh,seed);

		nh = Integer.parseInt(console.readLine("Introduce el nº grupos:"));
		seed = Integer.parseInt(console.readLine("Introduce el random seed:"));
		Grupos gs = new Grupos(nh,seed);

		System.out.println("Escoja el método de generación de la solución inicial:");
		System.out.println("  0. Distribuido\n  1. Aleatorio");
		int initOpt = Integer.parseInt(console.readLine(">"));
		initOpt %= 2;

		Estado e = new Estado(cs,gs, initOpt);

		System.out.println("Escoja el tipo de solucionador que desea aplicar:");
		System.out.println("  0. Hill climbing\n  1. Simulated annealing");
		String st = console.readLine(">");
	}
}