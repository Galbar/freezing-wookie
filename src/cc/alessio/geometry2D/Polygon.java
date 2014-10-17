package cc.alessio.geometry2D;

import java.util.ArrayList;

public class Polygon {
	ArrayList<Edge> edges;
	ArrayList<Point> vertex;
	Point bound_min;
	Point bound_max;
	double minDistanceBetweenVertex;

	public Polygon(double minDistanceBetweenVertex) {
		edges = new ArrayList<Edge>();
		vertex = new ArrayList<Point>();
		bound_min = null;
		bound_max = null;
		this.minDistanceBetweenVertex = minDistanceBetweenVertex;
	}

	public void add(double x1, double y1, double x2, double y2) {
		if (x2 - minDistanceBetweenVertex < x1 && x2 + minDistanceBetweenVertex > x1
					&& y2 - minDistanceBetweenVertex < y1 && y2 + minDistanceBetweenVertex > y1) return;
		edges.add(new Edge(x1, y1, x2, y2));
		boolean vertex1Exists = false;
		boolean vertex2Exists = false;
		for (int i = 0; i < vertex.size(); ++i) {
			Point p = vertex.get(i);
			if (!vertex1Exists)
				vertex1Exists = (p.x - minDistanceBetweenVertex < x1 && p.x + minDistanceBetweenVertex > x1
					&& p.y - minDistanceBetweenVertex < y1 && p.y + minDistanceBetweenVertex > y1);
			if (!vertex2Exists)
				vertex2Exists = (p.x - minDistanceBetweenVertex < x2 && p.x + minDistanceBetweenVertex > x2
					&& p.y - minDistanceBetweenVertex < y2 && p.y + minDistanceBetweenVertex > y2);
		}
		if (!vertex1Exists) 
			vertex.add(new Point(x1, y1));
		if (!vertex2Exists)
			vertex.add(new Point(x2, y2));

		if (bound_min == null) {
			bound_min = new Point(x1, y1);
		}
		if (bound_max == null) {
			bound_max	 = new Point(x1, y1);
		}

		if (bound_min.x > x1) bound_min.x = x1;
		if (bound_min.y > y1) bound_min.y = y1;
		if (bound_min.x > x2) bound_min.x = x2;
		if (bound_min.y > y2) bound_min.y = y2;

		if (bound_max.x < x1) bound_max.x = x1;
		if (bound_max.y < y1) bound_max.y = y1;
		if (bound_max.x < x2) bound_max.x = x2;
		if (bound_max.y < y2) bound_max.y = y2;
	}

	public void add(Point p1, Point p2) {
		add(p1.x, p1.y, p2.x, p2.y);
	}

	public int size() {
		return edges.size();
	}

	public Edge get(int i ) {
		return edges.get(i);
	}

	public int vertexSize() {
		return vertex.size();
	}

	public Point vertexGet(int i ) {
		return vertex.get(i);
	}

	public boolean isContained(Point p) {
		if (edges.size() == 0) return false;
		ArrayList<Line> lines = new ArrayList<Line>();
		for (int i = 0; i < edges.size(); ++i) {
			Edge edge = edges.get(i);
			if (edge.start.y <= p.y && edge.end.y > p.y ||
				edge.start.y > p.y && edge.end.y <= p.y) {
				lines.add(edge.getLine());
			}
		}
		RayCast r = new RayCast(bound_min.x, bound_max.x, bound_min.y, bound_max.y, minDistanceBetweenVertex, 0, minDistanceBetweenVertex);
		return (r.numberOfIntersections(lines, p) % 2 == 1);
	}

	public boolean isContained(double x, double y) {
		return isContained(new Point(x, y));
	}

	public Point getMinBound() {
		return bound_min;
	}

	public Point getMaxBound() {
		return bound_max;
	}
}