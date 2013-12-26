package rs.ac.ni.pmf.game_engine.classes;

public class CollisionDetector {

	public static boolean detectCollision(Shape first, Shape second) {
		if((first instanceof CircleShape) && (second instanceof CircleShape)) {
			return circlesIntercect((CircleShape) first, (CircleShape) second);
		}
		return false;
	}
	
	private static boolean circlesIntercect(CircleShape first,
			CircleShape second) {
		double x1 = first.getXPos();
		double y1 = first.getYPos();
		double x2 = second.getXPos();
		double y2 = second.getYPos();
		
		if((x1-x2)*(x1-x2) + (y1 - y2)*(y1-y2) < (first.getRadius() + second.getRadius()) * (first.getRadius() + second.getRadius())) {
			return true;
		}
		return false;
	}

	public static boolean detectCollision(GameObject first, GameObject second) {
		return detectCollision(first.getShape(), second.getShape());
	}
}
