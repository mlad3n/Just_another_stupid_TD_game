package rs.ac.ni.pmf.game_engine.classes;

import android.graphics.Canvas;

/**
 * 
 * @author mladen
 * 
 *         Klasa koja je wrapper za sve objekte u igrici, pokretne i
 *         staticke aktere.
 */
public abstract class GameObject {

	private boolean _destructFlag;
	protected Shape _shape;
	
	public abstract void update();

	public abstract void render(Canvas c);

	public abstract void handleEvents();

	public abstract void check(GameObject other); 

	public GameObject(Shape shape) {
		_shape = shape;
		setDestructFlag(false);
	}
	
	public Shape getShape() {
		return _shape;
	}
	
	public void setShape(Shape shape) {
		_shape = shape;
	}

	public boolean getDestructFlag() {
		return _destructFlag;
	}

	public void setDestructFlag(boolean destructFlag) {
		_destructFlag = destructFlag;
	}

	
	
}
