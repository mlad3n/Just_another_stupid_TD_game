package rs.ac.ni.pmf.game_engine.classes;

import rs.ac.ni.pmf.game_engine.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class NumberView extends View {

	private int _width, _height;
	private int _widthParentRelated,_heightParentRelated;
	private int _value;
	private int _numValueDigits;
	private int _lengthInDigits;
	private int[] _digits;
	private Bitmap[] _digitBitmaps;
	private int _orientation;
	private Bitmap _sourceBmp;
	private Bitmap _scaleBmp;
	private Canvas _bufferCanvas;

	public NumberView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NumberView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.NumberView, 0, 0);

		try {
			_value = a.getInt(R.styleable.NumberView_startValue, 0);
			_lengthInDigits = a.getInt(R.styleable.NumberView_numOfDigits, 1);
			_orientation = a.getInt(R.styleable.NumberView_orientation, 0);
			_widthParentRelated = a.getInt(R.styleable.NumberView_parentWidthScale, 1);
			_heightParentRelated = a.getInt(R.styleable.NumberView_parentHeightScale, 1);
		} finally {
			a.recycle();
		}
		
		_digits = new int[10];

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;

		_scaleBmp = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.sample, options);
		_sourceBmp = Bitmap.createScaledBitmap(_scaleBmp, _lengthInDigits * 48, 72, false);
		_bufferCanvas = new Canvas(_sourceBmp);
		
		_digitBitmaps = new Bitmap[10];
		_digitBitmaps[0] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.nula, options);
		_digitBitmaps[1] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.jedan, options);
		_digitBitmaps[2] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.dva, options);
		_digitBitmaps[3] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tri, options);
		_digitBitmaps[4] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cetri, options);
		_digitBitmaps[5] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.pet, options);
		_digitBitmaps[6] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.sest, options);
		_digitBitmaps[7] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.sedam, options);
		_digitBitmaps[8] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.osam, options);
		_digitBitmaps[9] = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.devet, options);
		
	
	}

	public NumberView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int parentWidth = MeasureSpec.getSize(widthMeasureSpec)/_widthParentRelated;
		int parentHeight = MeasureSpec.getSize(heightMeasureSpec)/_heightParentRelated;
		if (_orientation == 0) {
			_width = parentHeight;
			_height = parentWidth;
		} else {
			_width = parentWidth;
			_height = parentHeight;
		}
		this.setMeasuredDimension(parentWidth, parentHeight);
		Log.d("TAG", _width + " " + _height  );
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Bitmap temp1;
		
		calculate();
		draw_on_bitmap(_bufferCanvas);
		_sourceBmp.setDensity(Bitmap.DENSITY_NONE);
		if (_orientation == 0){
			 // calculate the scale - in this case = 0.4f
	        float scaleWidth = ((float) _width) / _sourceBmp.getWidth();
	        float scaleHeight = ((float) _height) / _sourceBmp.getHeight();
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			matrix.postRotate(270);
			temp1 = Bitmap.createBitmap(_sourceBmp , 0, 0, _sourceBmp.getWidth(), _sourceBmp.getHeight(), matrix, true);
		} else {
			temp1 = Bitmap.createScaledBitmap(_sourceBmp, _width, _height, false);
		}
		Paint blank = new Paint();
		blank.setColor(0xFFFFFF00);
		canvas.drawPaint(blank);
		canvas.drawBitmap(temp1, 0, 0, null);	
		
	}

	private void draw_on_bitmap(Canvas canvas) {
		int i;
		int j;
		for (i = 0; i<_lengthInDigits - _numValueDigits; i++)
			canvas.drawBitmap(_digitBitmaps[0], i*48, 0, null);
		for ( j = _numValueDigits-1; j>-1; j--){
			canvas.drawBitmap(_digitBitmaps[_digits[j]], (_lengthInDigits-j-1)*48, 0, null);

		}
		Log.d("TAG",i + " " + j + " " + _lengthInDigits + " " + _numValueDigits);
	}

	private void calculate() {
		int tmp = _value;
		_numValueDigits = 0;
		while (tmp != 0){
			_digits[_numValueDigits++] = tmp%10;
			tmp = tmp/10;
		}
	}

	public void setValue(int value) {
		_value = value;
		invalidate();
		requestLayout();	
	}

}
