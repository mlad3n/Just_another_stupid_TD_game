package rs.ac.ni.pmf.game_engine.classes;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

 public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

	private GameThread _thread;
	private int _xOffset = 0, _yOffset = 0;
	private int _mapSizeX, _mapSizeY;
	private int _screenWidth = 0;
	private int _screenHeight = 0;

	public GameSurface(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getHolder().addCallback(this);
		_thread = new GameThread(getHolder(), this);
		setFocusable(true);
	}

	public GameSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
		_thread = new GameThread(getHolder(), this);
		setFocusable(true);
	}

	public GameSurface(Context context) {
		super(context);
		getHolder().addCallback(this);
		_thread = new GameThread(getHolder(), this);
		setFocusable(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		_screenWidth = MeasureSpec.getSize(widthMeasureSpec);
		_screenHeight = MeasureSpec.getSize(heightMeasureSpec);
		this.setMeasuredDimension(_screenWidth, _screenHeight);
		Log.d("TAG", _screenWidth + " " + _screenHeight  );
	}

	public int get_sizeX() {
		return _mapSizeX;
	}

	public void setSizeX(int sizeX) {
		_mapSizeX = sizeX;
	}

	public int get_sizeY() {
		return _mapSizeY;
	}

	public void setSizeY(int sizeY) {
		_mapSizeY = sizeY;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		_thread.setRunning(true);
		_thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		_thread.setRunning(false);
	}

	public int get_xOffset() {
		return _xOffset;
	}

	public int get_yOffset() {
		return _yOffset;
	}


	public void move(int distanceX, int distanceY) {
		//Pomeri x
		if(_xOffset - distanceX <= 0 && _xOffset - distanceX >= -_mapSizeX + _screenWidth) {
			_xOffset -= distanceX;
		} else {
			if (_xOffset - distanceX > 0){
				_xOffset = 0;
			} else {
				_xOffset = -_mapSizeX + _screenWidth;
			}
		}
		
		//Pomeri y
		if(_yOffset - distanceY <= 0 && _yOffset - distanceY >= -_mapSizeY + _screenHeight) {
			_yOffset -= distanceY;
		} else {
			if (_yOffset - distanceY > 0){
				_yOffset = 0;
			} else {
				_yOffset = -_mapSizeY + _screenHeight;
			}
		}
		
	}

}
