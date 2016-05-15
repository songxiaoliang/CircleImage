package co.songlcy.circleimage.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import co.songlcy.circleimage.R;

/**
 * Created by Song on 2016/5/14.
 */
public class CircleImage extends ImageView{

    private int mWidth;
    private int circleX;
    private int circleY;
    private int mRadius;

    private Paint mPaint;
    private Paint mBorderPaint;

    private Bitmap mBitmap;
    private Bitmap mSrcBitmap;
    private BitmapShader mBitmapShader;

    private int imageBorderWidth;
    private int imageBorderColor;

    public CircleImage(Context context) {
        this(context, null);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleImage,0,0);

        int attrNum = ta.getIndexCount();

        for (int i = 0; i < attrNum; i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.CircleImage_imgBorderWidth) {
                imageBorderWidth = ta.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,2,getResources().getDisplayMetrics()));
            } else {
                imageBorderColor = ta.getColor(attr, Color.YELLOW);
            }
        }

        ta.recycle();
        initPaint();
    }

    /**
     * init paint
     */
    private void initPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(imageBorderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        mBorderPaint.setStrokeJoin(Paint.Join.ROUND);
        mBorderPaint.setStrokeWidth(imageBorderWidth);
    }

    /**
     * setting bitmap to bitmapShader
     */
    private void init() {

        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        mBitmap = drawableToBitamp(drawable);
        mSrcBitmap = Bitmap.createScaledBitmap(mBitmap, mWidth, mWidth, false);
        mBitmapShader = new BitmapShader(mSrcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        mWidth = Math.min(width,height);
        circleX = mWidth / 2;
        circleY = mWidth / 2;
        mRadius = (mWidth - imageBorderWidth * 2) / 2;
        setMeasuredDimension(mWidth, mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        init();
        canvas.drawCircle(circleX, circleY, mRadius, mPaint);
        canvas.drawCircle(circleX, circleY, mRadius + imageBorderWidth / 2, mBorderPaint);
    }

    /**
     * drawable to bitmap
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
}
