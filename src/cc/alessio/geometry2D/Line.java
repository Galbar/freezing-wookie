package cc.alessio.geometry2D;

import java.lang.Double;

public class Line {
	double m, b;
	boolean isVertical;
	boolean isHorizontal;

	private void init(double x1, double y1, double x2, double y2) {
		isVertical = false;
		isHorizontal = false;
		if (x2 - x1 == 0) {
			isVertical = true;
			b = x1;
		}
		else if (y1 - y2 == 0) {
			isHorizontal = true;
			b = y1;
		}
		else {
			m = (y2 - y1) / (x2 - x1);
			b = y1 - m * x1;
		}
	}

	public Line(Point p1, Point p2) {
		init(p1.x, p1.y, p2.x, p2.y);
	}

	public Line(double x1, double y1, double x2, double y2) {
		init(x1, y1, x2, y2);
	}

	public Point getValues(Point p) {
		if (isVertical) {
			return new Point(b, p.y);
		}
		else if (isHorizontal) {
			return new Point(p.x, b);
		}
		double x = (p.y - b) / m;
		double y = m * p.x + b;
		return new Point(x, y);
	}

	public Point getValues(double x, double y) {
		return getValues(new Point(x, y));
	}

	public boolean isVertical() {
		return isVertical;
	}

	public boolean isHorizontal() {
		return isHorizontal;
	}
}