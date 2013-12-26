package rs.ac.ni.pmf.game_engine.classes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class ContainerSurfaceView extends View {
	
	
	private GameSurface _gameSurface;
	// private GestureDetectorCompat _gDetector;
	
	private int _mapSizeX, _mapSizeY;
	private int _screenWidth = 0;
	private int _screenHeight = 0;
	private int _xOffset = 0; 
	private int _yOffset = 0;

	private static int NONE = 0;
	private static int DRAG = 1;
	private static int ZOOM = 2;
	private static float MIN_ZOOM = 1f;
	private static float MAX_ZOOM = 5f;
	
	private ScaleGestureDetector _detector;
	private float _scaleFactor = 1.f;
	private int _mode;
	private float _startX = 0f;
	private float _startY = 0f;
	private float _translateX = 0f;
	private float _translateY = 0f;
	private float _previousTranslateX = 0f;
	private float _previousTranslateY = 0f;
	
	public ContainerSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		_detector = new ScaleGestureDetector(context, new ScaleListener());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int desiredWidth = 100;
		int desiredHeight = 100;

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width;
		int height;

		// Measure Width
		if (widthMode == MeasureSpec.EXACTLY) {
			// Must be this size
			width = widthSize;
		} else if (widthMode == MeasureSpec.AT_MOST) {
			// Can't be bigger than...
			width = Math.min(desiredWidth, widthSize);
		} else {
			// Be whatever you want
			width = desiredWidth;
		}

		// Measure Height
		if (heightMode == MeasureSpec.EXACTLY) {
			// Must be this size
			height = heightSize;
		} else if (heightMode == MeasureSpec.AT_MOST) {
			// Can't be bigger than...
			height = Math.min(desiredHeight, heightSize);
		} else {
			// Be whatever you want
			height = desiredHeight;
		}

		// MUST CALL THIS
		setMeasuredDimension(width, height);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			// The first finger has been pressed. The only action that the user
			// can take now is to pan/drag so let's
			// set the mode to DRAG
			_mode = DRAG;
			_startX = ev.getX();
			_startY = ev.getY();
			_startX = ev.getX() - _previousTranslateX;
			_startY = ev.getY() - _previousTranslateY;
			break;

		case MotionEvent.ACTION_MOVE:
			// We don't need to set the mode at this point because the mode is
			// already set to DRAG
			_translateX = ev.getX() - _startX;
			_translateY = ev.getY() - _startY;
			// ovde bi iso move;
			break;

		case MotionEvent.ACTION_POINTER_DOWN:
			// The second finger has been placed on the screen and so we need to
			// set the mode to ZOOM
			_mode = ZOOM;
			break;

		case MotionEvent.ACTION_UP:
			// All fingers are off the screen and so we're neither dragging nor
			// zooming.
			_mode = NONE;
			_previousTranslateX = _translateX;
			_previousTranslateY = _translateY;
			break;

		case MotionEvent.ACTION_POINTER_UP:
			// The second finger is off the screen and so we're back to
			// dragging.
			_mode = DRAG;
			_previousTranslateX = _translateX;
			_previousTranslateY = _translateY;
			break;
		}

		_detector.onTouchEvent(ev);
		if ((_mode == DRAG && _scaleFactor != 1f) || _mode == ZOOM) {
			invalidate();
		}
		return true;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
 
		canvas.save();
 
		canvas.scale(_scaleFactor, _scaleFactor, _detector.getFocusX(), _detector.getFocusY());

		if((_translateX * -1) < 0) {
			_translateX = 0;
	//	}else if((_translateX * -1) > (_scaleFactor - 1) * displayWidth) {
	//		_translateX = (1 - _scaleFactor) * displayWidth;
		}
	
		if(_translateY * -1 < 0) {
			_translateY = 0;
	//	}else if((_translateY * -1) > (_scaleFactor - 1) * displayHeight) {
	//		_translateY = (1 - _scaleFactor) * displayHeight;
		}
		
		canvas.translate(_translateX / _scaleFactor, _translateY / _scaleFactor);
		canvas.restore();
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			_scaleFactor *= detector.getScaleFactor();
			_scaleFactor = Math.max(MIN_ZOOM, Math.min(_scaleFactor, MAX_ZOOM));
			return true;
		}
	}
}
