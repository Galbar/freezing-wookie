package  IA.freezing-wookie

class Estado {
	/**
	* Lista de grupos asignados a los helicopteros
	*/
	private int[] grupos;

	/**
	* Coste del estado actual
	*/
	private int cost;

	/**
	* Constructor
	*/
	public Estado(int ng) {
		coste = 0;
		grupo = new int[ng];
	}

	/**
	* Asigna un helicoptero a un grupo
	*/
	public void asignar(int g, int h) {
		grupo[g] = h;
		// TODO: implementar funcion de calcular costes de rescatar g per h 
	} 

	/**
	* Intercambia los helicopteros de dos grupos
	*/
	public void intercambia(int g1, int g2) {
		int tmp = grupo[g1];
		grupo[g1] = grupo[g2];
		grupo[g2] = tmp;
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
}