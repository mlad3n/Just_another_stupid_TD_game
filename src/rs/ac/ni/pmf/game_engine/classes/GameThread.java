package rs.ac.ni.pmf.game_engine.classes;

import java.util.ArrayList;
import java.util.List;
import rs.ac.ni.pmf.game_engine.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

/**
 * 
 * 
 * Glavni thread u igri, pokrece update-render petlju.
 * 
 */

public class GameThread extends Thread {

	private Paint _blank;
	private SurfaceHolder _surfaceHolder;
	private GameSurface _surface;
	private ArrayList<GameObject> _gameObjects;
	// private ArrayList<Detail> _gameDetails;
	private boolean _isRunning;
	private Canvas _canvas = null;
	private LevelMap _levelMap;

	private Bitmap _srcBitmap;
	private Bitmap _bufferBitmap;
	private Canvas _bufferCanvas;

	public GameThread(SurfaceHolder holder, GameSurface surface) {
		super();
		_surfaceHolder = holder;
		_surface = surface;

		_gameObjects = new ArrayList<GameObject>();

		_levelMap = new LevelMap(10, 10, 128, 128, _surface);
		
		_surface.setSizeX(_levelMap.getPixelWidth());
		_surface.setSizeY(_levelMap.getPixelHeight());

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;

		_srcBitmap = BitmapFactory.decodeResource(_surface.getResources(),
				R.drawable.sample, options);
		_bufferBitmap = Bitmap
				.createScaledBitmap(_srcBitmap, _levelMap.getPixelWidth(), _levelMap.getPixelHeight(), false);
		_bufferCanvas = new Canvas(_bufferBitmap);

		_blank = new Paint();
		_blank.setColor(0xFFFFFFFF);
	}

	public void setRunning(boolean b) {
		_isRunning = b;
	}

	public void run() {
		while (_isRunning) {
			updateGameObjects();
			detectCollisions(); // ??
			renderScene();
			_canvas = _surfaceHolder.lockCanvas(null);
			_canvas.drawPaint(_blank);
			_canvas.drawBitmap(_bufferBitmap, _surface.get_xOffset(),
					_surface.get_yOffset(), null);
			_surfaceHolder.unlockCanvasAndPost(_canvas);
			Log.d("PETLJA", "AAAAAAA " + _surface.get_xOffset() + " "
					+ _surface.get_yOffset());
		}
	}

	private void renderScene() {
		renderBackground();
		renderObjects();
	}

	/**
	 * Odredjuje nove pozicije i stanja objekata.
	 */
	private void updateGameObjects() {
		// TODO Auto-generated method stub
	}

	/**
	 * Pronalazi kolizije izmedju objekata, i po potrebi azurira objekte i
	 * kreira nove (npr. ako se dva objekta sudare, promeni im stanje i napravi
	 * objekat ekplozija).
	 */
	private void detectCollisions() {
		// TODO Auto-generated method stub
		List<GameObject> newObjects = new ArrayList<GameObject>();
		for (int i = 0; i < _gameObjects.size() - 1; i++) {
			for (int j = i + 1; j < _gameObjects.size(); j++) {
				GameObject first = _gameObjects.get(i);
				GameObject second = _gameObjects.get(j);

				boolean collides = CollisionDetector.detectCollision(first,
						second);
				if (collides) {
					GameObject newObject = createCollisionObject(first, second);
					if (!alreadyInGameObjectList(newObject, newObjects)) {
						newObjects.add(newObject);
					}
				}
			}
		}
		removeOldObjects();
		addNewObjects(newObjects);
	}

	/**
	 * Eventualno moze jos neka provera da li objekat treba dodati.
	 * 
	 * @param newObjects
	 */
	private void addNewObjects(List<GameObject> newObjects) {
		for (GameObject obj : newObjects) {
			_gameObjects.add(obj);
		}
	}

	/**
	 * Ovo treba da iz liste objekata obrise objekte koji su pri kolizijama
	 * nestali. Treba nekako razresiti obelezavanje objekata koji se brisu.
	 */
	private void removeOldObjects() {
		for (GameObject obj : _gameObjects) {
			if (!obj.getDestructFlag())
				_gameObjects.remove(obj);
		}
	}

	/**
	 * Proverava da li novi objekat treba dodati u listu objekata. Recimo, ako
	 * se sudare cetri objekta, potencijalno imam sest novih GameObject-a. Ovo
	 * treba da na neki nacin odluci da li se svaki od tih sest (a parametar je
	 * samo jedan od njih) doda u listu objekata ili ne.
	 * 
	 * @param newObject
	 * @param newObjects
	 * @return
	 */
	private boolean alreadyInGameObjectList(GameObject newObject,
			List<GameObject> newObjects) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Pravi novi objekat na osnovu dva objekta koji su se sudarili.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	private GameObject createCollisionObject(GameObject first, GameObject second) {
		// TODO Auto-generated method stub
		return null;
	}

	private void renderBackground() {
		// TODO Auto-generated method stub
		_levelMap.draw(_bufferCanvas);
		renderDetails(_bufferCanvas);
	}

	/**
	 * Iscrtava detalje koje takodje ne reaguje sa okolinom ali zahteva da se
	 * iscrta preko osnovnog tilemap-a Drvece, senke itd...
	 * 
	 */
	private void renderDetails(Canvas canvas) {
		// TODO Auto-generated method stub

	}

	private void renderObjects() {
		for (GameObject gameObject : _gameObjects) {
			gameObject.update();
			gameObject.render(_bufferCanvas);
		}
	}
}
