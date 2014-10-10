package freezingwookie;

import IA.Desastres.*;
import java.util.Random;
import java.io.Console;
import java.lang.Integer;

public class Estado {
	/**
	* Lista de grupos asignados a los helicopteros
	*/
	private int[] plan;

	/**
	* Coste del estado actual
	*/
	private int coste;

	/**
	* Centros de helicopteros
	*/
	Centros cs;

	/**
	* Grupos a rescatar
	*/
	Grupos gs;

	/**
	* Numero de centros
	*/
	int nc;

	/**
	* Numero de helicopteros
	*/
	int nh;

	/**
	* Numero de grupos
	*/
	int ng;

	/**
	* Constructor
	*/
	public Estado() {
		Console console = System.console();
		int[] ret = new int[3];
		nc = Integer.parseInt(console.readLine("Introduce el nº centros:"));
		nh = Integer.parseInt(console.readLine("Introduce el nº helicopteros:"));
		ng = Integer.parseInt(console.readLine("Introduce el nº grupos:"));
		coste = 0;
		plan = new int[ng];
	}

	/**
	* Asigna un helicoptero a un grupos
	*/
	public void asignar(int g, int h) {
		// TODO: implement
	} 

	/**
	* Intercambia los helicopteros de dos grupos
	*/
	public void intercambia(int g1, int g2) {
		// TODO: implement
	}

	/**
	* Calcula una primera solucion con una distribucion no optima
	*/
	public void primeraSol() {
		// TODO: implement
	}

	/**
	* Mejora la solucion actual
	*/
	public void mejora() {
		// TODO: implement
	}

	/**
	* Imprimir estado
	*/
	public void pintaEstado() {
		// TODO: implement
	}
}
