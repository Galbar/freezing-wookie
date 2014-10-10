package freezingwookie;

public class ControladorEstado {

	/**
	* Main
	*/
	public static void main(String[] args) {

		Estado e = new Estado();

		e.primeraSol();

		e.mejora();

		e.pintaEstado();
	}
}