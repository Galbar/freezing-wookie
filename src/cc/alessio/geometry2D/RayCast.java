package cc.alessio.geometry2D;

import java.util.ArrayList;
import java.lang.Integer;

public class RayCast {
	private double min_x, min_y, max_x, max_y; // Boundaries
	private double epsilon;
	private double dir_x, dir_y; // Direction vector.

	public RayCast(double min_x, double max_x, double min_y, double max_y, double dir_x, double dir_y, double eps) {
		this.min_x = min_x;
		this.min_y = min_y;
		this.max_x = max_x;
		this.max_y = max_y;
		epsilon = eps;
		this.dir_x = dir_x;
		this.dir_y = dir_y;
	}

	public int numberOfIntersections(ArrayList<Line> lines, double x, double y) {
		int count = 0;
		ArrayList<Boolean> intersected = new ArrayList<Boolean>();
		for (int i = 0; i < lines.size(); ++i) {
			intersected.add(false);
		}
		for (; x >= min_x && x <= max_x && y >= min_y && y <= max_y; x += dir_x, y += dir_y) {
			for (int i = 0; i < lines.size(); ++i) {
				Line line = lines.get(i);
				if (intersected.get(i)) continue;
				Point p = line.getValues(x, y);
				if ((p.y >= (y - epsilon) && p.y < (y + epsilon))
					&& (p.x >= (x - epsilon) && p.x < (x + epsilon))) {
						count++;
					intersected.set(i, true);
				}
			}
		}
		return count;
	}

	public int numberOfIntersections(ArrayList<Line> lines, Point p) {
		return numberOfIntersections(lines, p.x, p.y);
	}

	public ArrayList<Integer> intersections(ArrayList<Line> lines, double x, double y) {
		ArrayList<Integer> intersections = new ArrayList<Integer>();
		ArrayList<Boolean> intersected = new ArrayList<Boolean>();
		for (int i = 0; i < lines.size(); ++i) {
			intersected.add(false);
		}
		for (; x >= min_x && x <= max_x && y >= min_y && y <= max_y; x += dir_x, y += dir_y) {
			for (int i = 0; i < lines.size(); ++i) {
				Line line = lines.get(i);
				if (intersected.get(i)) continue;
				Point p = line.getValues(x, y);
				if ((p.y >= (y - epsilon) && p.y < (y + epsilon))
					&& (p.x >= (x - epsilon) && p.x < (x + epsilon))) {
					intersections.add(i);
					intersected.set(i, true);
				}
			}
		}
		return intersections;
	}

	public ArrayList<Integer> intersections(ArrayList<Line> lines, Point p) {
		return intersections(lines, p.x, p.y);
	}
}