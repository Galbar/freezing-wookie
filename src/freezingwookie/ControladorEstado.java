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

		Estado e = new Estado(cs,gs);

		e.primeraSol();

		e.mejora();

		e.pintaEstado();
	}
}