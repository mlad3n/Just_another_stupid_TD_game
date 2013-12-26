package rs.ac.ni.pmf.game_engine.classes;

public abstract class Shape {	 
	private int _xPos;
	private int _yPos;	

	public Shape(int xPos, int yPos) {
		_xPos = xPos;
		_yPos = yPos;
	}

	public int getXPos() {
		return _xPos;
	}

	public void setXPos(int xPos) {
		_xPos = xPos;
	}

	public int getYPos() {
		return _yPos;
	}

	public void setYPos(int yPos) {
		_yPos = yPos;
	}

}
