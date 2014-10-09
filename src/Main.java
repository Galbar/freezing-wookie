package  IA.freezingwookie;

import IA.Desastres.*;
import java.util.Random;
import java.io.Console.*;
import java.lang.Integer;

class Main {

	/**
	* Numero de helicopteros
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
	* Centros de helicopteros
	*/
	public Centros cs;

	/**
	* Grupos a rescatar
	*/
	public Grupos gs;

	/**
	* Estado con el que se generara la solucion
	*/
	public Estado e;

	/**
	* Lee los datos de entrada
	*/
	public void reader() {
		Console console = System.console();
		nc = Interger.parseInt(console.readLine("Introduce el nº centros:"));
		nh = Interger.parseInt(console.readLine("Introduce el nº helicopteros:"));
		ng = Interger.parseInt(console.readLine("Introduce el nº grupos:"));
	}

	/**
	* Main
	*/
	public static void main(String[] args) {
		reader();
		Random rand = new Random();
		cs = new Centros(nc,nh,rand.nextInt());
		gs = new Grupos(ng,rand.nextInt());
	}
}