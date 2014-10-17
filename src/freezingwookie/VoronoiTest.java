package freezingwookie;

import be.humphreys.simplevoronoi.Voronoi;
import be.humphreys.simplevoronoi.GraphEdge;
import cc.alessio.geometry2D.*;
import java.io.Console;
import java.lang.Double;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


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
		double[] in_x = new double[5];
		double[] in_y = new double[5];
		in_x[0] = 0;
		in_y[0] = 0;
		in_x[1] = 10;
		in_y[1] = 10;
		in_x[2] = 10;
		in_y[2] = 0;
		in_x[3] = 0;
		in_y[3] = 10;
		in_x[4] = 5;
		in_y[4] = 5;
		List<GraphEdge> list = voronoi.generateVoronoi(in_x, in_y, 0, 10, 0, 10);
		ArrayList<Polygon> areas = new ArrayList<Polygon>();
		for (int i = 0; i < 5; ++i) {
			areas.add(new Polygon(0.0001));
		}

		for (int i = 0; i < list.size(); ++i) {
			GraphEdge ge = list.get(i);
			areas.get(ge.site1).add(ge.x1, ge.y1, ge.x2, ge.y2);
			areas.get(ge.site2).add(ge.x1, ge.y1, ge.x2, ge.y2);
		}

		// Close areas
		for (int i = 0; i < areas.size(); ++i) {
			Polygon area = areas.get(i);
			System.out.print("Area ");
			System.out.println(i);
			System.out.print("Edges: ");
			System.out.print(area.size());
			System.out.print("\tVertex: ");
			System.out.println(area.vertexSize());
			for (int j = 0; j < area.vertexSize(); ++j) {
				System.out.print("\tVertex ");
				System.out.print(j);
				System.out.print(": (");
				System.out.print(area.vertexGet(j).x);
				System.out.print(", ");
				System.out.print(area.vertexGet(j).y);
				System.out.println(")");
			}
			if (area.size() != area.vertexSize()) {
				if (area.getMinBound().y == 0)
					area.add(new Point(area.getMinBound().x, 0), new Point(area.getMaxBound().x, 0));
				if (area.getMaxBound().y == 10)
					area.add(new Point(area.getMinBound().x, 10), new Point(area.getMaxBound().x, 10));
				if (area.getMinBound().x == 0)
					area.add(new Point(0, area.getMinBound().y), new Point(0, area.getMaxBound().y));
				if (area.getMaxBound().x == 10)
					area.add(new Point(10, area.getMinBound().y), new Point(10, area.getMaxBound().y));
			}
			for (int j = 0; j < area.size(); ++j) {
				System.out.print("\tEdge ");
				System.out.print(j);
				System.out.print(": (");
				System.out.print(area.get(j).start.x);
				System.out.print(", ");
				System.out.print(area.get(j).start.y);
				System.out.print(")-(");
				System.out.print(area.get(j).end.x);
				System.out.print(", ");
				System.out.print(area.get(j).end.y);
				System.out.println(")");
			}
		}

		Console console = System.console();
		Point p;
		while (true) {
			double x = Double.parseDouble(console.readLine("p.x:"));
			double y = Double.parseDouble(console.readLine("p.y:"));
			p = new Point(x, y);
			for (int i = 0; i < areas.size(); ++i) {
				System.out.print("mirando sitio ");
				System.out.println(i);
				if (areas.get(i).isContained(p)) {
					System.out.print("\tcontenido en sitio ");
					System.out.println(i);
				}
			}
		}
	}
}