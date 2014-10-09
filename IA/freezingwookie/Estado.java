package  IA.freezingwookie;

import IA.Desastres.*;
import java.util.Random;
import java.io.Console;
import java.lang.Integer;

class Estado {
	/**
	* Lista de grupos asignados a los helicopteros
	*/
	private int[] grupos;

	/**
	* Coste del estado actual
	*/
	private int coste;

	/**
	* Constructor
	*/
	public Estado(int ng) {
		coste = 0;
		grupos = new int[ng];
	}

	/**
	* Asigna un helicoptero a un grupos
	*/
	public void asignar(int g, int h) {
		grupos[g] = h;
		// TODO: implementar funcion de calcular costes de rescatar g per h 
	} 

	/**
	* Intercambia los helicopteros de dos grupos
	*/
	public void intercambia(int g1, int g2) {
		int tmp = grupos[g1];
		grupos[g1] = grupos[g2];
		grupos[g2] = tmp;
		// TODO: actualizar costes
	}

	/**
	* Calcula una primera solucion con una distribucion no optima
	*/
	public void primeraSol() {
		// TODO: function of first State creator
	}

	/**
	* Imprimir estado
	*/
	public void pintaEstado() {
		// TODO: all
	}

	// TEST

	/**
	* Lee los datos de entrada
	*/
	static public int[] reader() {
		Console console = System.console();
		int[] ret = new int[3];
		ret[0] = Integer.parseInt(console.readLine("Introduce el nº centros:"));
		ret[1] = Integer.parseInt(console.readLine("Introduce el nº helicopteros:"));
		ret[2] = Integer.parseInt(console.readLine("Introduce el nº grupos:"));
		return ret;
	}

	/**
	* Main
	*/
	public static void main(String[] args) {

		/**
		* Centros de helicopteros
		*/
		Centros cs;

		/**
		* Grupos a rescatar
		*/
		Grupos gs;

		int[] a = reader();

		/**
		* Numero de helicopteros
		*/
		int nc = a[0];

		/**
		* Numero de helicopteros
		*/
		int nh = a[1];

		/**
		* Numero de grupos
		*/
		int ng = a[2];

		Random rand = new Random();
		cs = new Centros(nc,nh,rand.nextInt());
		gs = new Grupos(ng,rand.nextInt());
	}
}