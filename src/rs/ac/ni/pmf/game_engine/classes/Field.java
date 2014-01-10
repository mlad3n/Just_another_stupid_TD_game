package rs.ac.ni.pmf.game_engine.classes;


import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 
 * Izvedene klase ce da budu konkretne, nece imati nikakve dodatne funkcije samo
 * ce se u njihovim konstruktorima povezati resursi sa nizom sprite-ova
 *
 */
public abstract class Field {

	//komentar pedja
	private int _fieldState;
	protected Bitmap[] _sprites;
	
	
	public Field(){
		_fieldState = 0;
	}
	
	public int get_fieldState() {
		return _fieldState;
	}

	public void set_fieldState(int fieldState) {
		_fieldState = fieldState;
	}


	public void draw(int leftCoordinate, int topCoordinate, Canvas canvas) {
		canvas.drawBitmap(_sprites[_fieldState], leftCoordinate, topCoordinate, null);	
	}

}
