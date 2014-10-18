package freezingwookie;

import java.io.Console;

public class InputOutput {
	Console console = System.console();

	/**
	 * Comprueba si la entrada del menu es correcta
	 * @param  i entrada del menu
	 * @return   cierto si la entrada es correcta, falso en caso contrario
	 */
	public boolean entradaCorrecta(int i) {
		if (i == 1 || i == 0) return true;
		if (i != -101) System.out.println("Entrada incorrecta, vuelva a introducir su opción");
		return false;
	}

	/**
	 * Obtiene por terminal el numero de centros
	 * @return numero de centros
	 */
	public int nCentros() {
		return Integer.parseInt(console.readLine("Introduce el nº centros:"));
	}

	/**
	 * Obtiene por terminal el numero de helicopteros
	 * @return numero de helicopteros
	 */
	public int nHelicopteros() {
		return Integer.parseInt(console.readLine("Introduce el nº helicopteros por grupo:"));
	}

	/**
	 * Obtiene por terminal un random seed
	 * @return random seed
	 */
	public int seed() {
		return Integer.parseInt(console.readLine("Introduce el random seed:"));
	}

	/**
	 * Obtiene por terminal el numero de grupos
	 * @return numero de grupos
	 */
	public int nGrupos() {
		return Integer.parseInt(console.readLine("Introduce el nº grupos:"));
	}

	/**
	 * Pregunta por terminal que tipo de solucion inicial se quiere utilizar
	 * @return solucion inicial escogida
	 */
	public int initOpt() {
		int init = -101;
		while (!entradaCorrecta(init)) {
			System.out.println("Escoja el método de generación de la solución inicial:");
			System.out.println("  0. Distribuido\n  1. Aleatorio");
			init = Integer.parseInt(console.readLine(">"));
		}
		return init;
	}

	/**
	 * Pregunta por terminal que tipo de algoritmo de busqueda local se quiere utilizar
	 * @return algoritmo de busqueda local que se quiere utilizar
	 */
	public int sol() {
		int st = -101;
		while (!entradaCorrecta(st)) {
			System.out.println("Escoja el tipo de solucionador que desea aplicar:");
			System.out.println("  0. Hill climbing\n  1. Simulated annealing");
			st = Integer.parseInt(console.readLine(">"));
		}
		return st;
	}

	/**
	 * Imprime el resultado inicial y el final
	 * @param e1   coste total estado inicial
	 * @param e2   cost maximo prioridad 1 estado inicial
	 * @param res1 coste total estado final
	 * @param res2 coste maximo prioridad 1 estado final
	 */
	public void imprimeResultado(double e1, double e2, double res1, double res2) {
		System.out.println("\nResultado:");
		System.out.println("\tTiempo inicial:\n\t\ttodos = "+ e1 + 
			"\n\t\tgrupos prioridad 1 = " + e2);
		System.out.println("\tTiempo final:\n\t\ttodos = "+ res1 + 
			"\n\t\tgrupos prioridad 1 = " + res2);
	}
}