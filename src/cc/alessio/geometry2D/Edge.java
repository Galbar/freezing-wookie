package cc.alessio.geometry2D;

public class Edge {
	public Point start;
	public Point end;

	public Edge(Point p1, Point p2) {
		this.start = p1;
		this.end = p2;
	}

	public Edge(double x1, double y1, double x2, double y2) {
		this.start = new Point(x1, y1);
		this.end = new Point(x2, y2);
	}

	public Line getLine() {
		return new Line(start, end);
	}
}