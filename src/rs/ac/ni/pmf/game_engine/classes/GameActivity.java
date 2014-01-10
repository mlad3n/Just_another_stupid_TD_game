package rs.ac.ni.pmf.game_engine.classes;


import rs.ac.ni.pmf.game_engine.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class GameActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

	private GameSurface _gameSurface;
	private GestureDetectorCompat _gDetector;
	private LinearLayout _score;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		setContentView(R.layout.activity_main);
		_gameSurface = (GameSurface) findViewById(R.id.screen);
		_score = (LinearLayout) findViewById(R.id.linearLayout1);
		_score.setBackgroundColor(Color.BLACK);
		_gDetector = new GestureDetectorCompat(this, this);
		_gDetector.setOnDoubleTapListener(this);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this._gDetector.onTouchEvent(event);
		// Be sure to call the superclass implementation
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// Log.d("TOUCH", "DOUBLETAP YEAA ");
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// Log.d("TOUCH", "DOUBLETAP_EVENT YEAA ");
		return true;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// Log.d("TOUCH", "SINGLETAP_CONFIRMED YEAA ");
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// Log.d("TOUCH", "ONDOWN YEAA ");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// Log.d("TOUCH", "FLING YEAA ");
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// Log.d("TOUCH", "LONGPRESS YEAA ");
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		_gameSurface.move((int) distanceX, (int) distanceY);
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// Log.d("TOUCH", "SHOWPRESS YEAA ");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// Log.d("TOUCH", "SINGLETAPUP YEAA ");
		return true;
	}

}
