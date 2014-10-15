package freezingwookie;

import be.humphreys.simplevoronoi.Voronoi;


public class VoronoiTest {
	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
	
	public static void main(String[] args) {
		Voronoi voronoi = new Voronoi(0.0001);
		double[] in_x = double[3];
		double[] in_y = double[3];
		for (int i = 0; i < 3; ++i) {

		}
	}
}