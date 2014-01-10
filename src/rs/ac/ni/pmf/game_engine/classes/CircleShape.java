package rs.ac.ni.pmf.game_engine.classes;

public class CircleShape extends Shape {

	public double _radius = 0.0f;

	public CircleShape(int xPos, int yPos, double radius) {
		super(xPos, yPos);
		_radius = radius;
	}

	public double getRadius() {
		return _radius;
	}

	public void setRadius(double radius) {
		_radius = radius;
	}
	
	
}
