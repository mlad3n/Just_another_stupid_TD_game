package rs.ac.ni.pmf.game_engine.sprites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import rs.ac.ni.pmf.game_engine.R;
import rs.ac.ni.pmf.game_engine.classes.Field;

public class Gras1_Tile1 extends Field {

	private SurfaceView _surface;
	
	public Gras1_Tile1(SurfaceView view){
		super();
		_surface = view;
		_sprites = new Bitmap[1];
		initializeSprites(_surface);
	}
	
	protected void initializeSprites(SurfaceView view) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;
		
		_sprites[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.trava1_plocice1, options);
	}
}
