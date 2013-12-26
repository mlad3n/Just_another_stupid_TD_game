package rs.ac.ni.pmf.game_engine.classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 
 * moze da se doda i ovde niz bitmapa za slucaj da se detalj promeni posle odredjenog vremena
 * drvo postane zuto posle nekog vremena itd
 *
 */

public class Detail {

	private Bitmap _bitmap;
	private int _xPos;
	private int _yPos;
	
	public Detail(int xPos, int yPos, Bitmap bitmap){
		_bitmap = bitmap;
		_xPos = xPos;
		_yPos = yPos;
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(_bitmap, _xPos - _bitmap.getWidth()/2, _yPos - _bitmap.getHeight()/2, null);
	}
}
