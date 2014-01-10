package rs.ac.ni.pmf.game_engine.classes;

import rs.ac.ni.pmf.game_engine.sprites.Gras1_Tile1;
import rs.ac.ni.pmf.game_engine.sprites.Grass1;
import rs.ac.ni.pmf.game_engine.sprites.Tiles1;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * 
 * 
 * 
 * @author Marko
 * 
 */

public class LevelMap {

	private Field[][] _levelMap;
	private int _fieldWidth;
	private int _fieldHeight;

	public LevelMap(int n, int m, int fieldWidth, int fieldHeight,
			SurfaceView surface) {
		_levelMap = new Field[n][m];
		_fieldHeight = fieldHeight;
		_fieldWidth = fieldWidth;
		init(surface);
	}

	/**
	 * Umesto ovih if-ova trebalo bi da se prosledi neka matrica parametara na
	 * osnovu koje ce pri prolask kroz nju u ovoj funkciji da poziva
	 * odgovarajuce konstruktore
	 * 
	 * @param surface
	 */
	private void init(SurfaceView surface) {
		int h = getHeight() / 3;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				if (i <= h) {
					_levelMap[i][j] = new Grass1(surface);
				} else if (i > h && i <= 2 * h) {
					_levelMap[i][j] = new Gras1_Tile1(surface);
				} else {
					_levelMap[i][j] = new Tiles1(surface);
				}
			}
		}
	}

	public int getHeight() {
		if (_levelMap == null) {
			return 0;
		}
		return _levelMap.length;
	}

	public int getWidth() {
		if (_levelMap == null || _levelMap.length == 0) {
			return 0;
		}
		return _levelMap[0].length;
	}

	public Field getField(int i, int j) {
		if (i < 0 || j < 0 || i > getHeight() || j > getWidth()) {
			return null;
		}
		return _levelMap[i][j];
	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				_levelMap[i][j].draw(j * _fieldWidth, i * _fieldHeight, canvas);
			}
		}
	}
	
	public int getPixelWidth(){
		return this.getWidth()*_fieldWidth;
	}
	
	public int getPixelHeight(){
		return getHeight()*_fieldHeight;
	}
	
}
