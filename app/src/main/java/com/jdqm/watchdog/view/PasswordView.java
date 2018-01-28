package com.jdqm.watchdog.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jdqm.watchdog.R;
import com.jdqm.watchdog.util.DisplayUtil;

/**
 * enter password view
 * <p>
 * Created by Jdqm on 2018/1/28.
 */

public class PasswordView extends View {
    private static final String TAG = "PasswordView";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int dotRadio;
    private int dotColor = Color.parseColor("#000000");
    private int circleRadio;
    private int circleColor = Color.parseColor("#000000");
    private int itemWidth;
    private StringBuilder password = new StringBuilder();

    public interface ResultListener{
        void onCorrect();
        void onError();
    }

    private ResultListener onResultListener;

    public void setOnResultListener(ResultListener onResultListener) {
        this.onResultListener = onResultListener;
    }

    public PasswordView(Context context) {
        this(context, null);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PasswordView);
        dotRadio = (int) ta.getDimension(R.styleable.PasswordView_dotRadio, DisplayUtil.dpToPixel(4));
        dotColor = ta.getColor(R.styleable.PasswordView_dotColor, dotColor);
        circleRadio = (int) ta.getDimension(R.styleable.PasswordView_circleRadio, DisplayUtil.dpToPixel(10));
        ta.getColor(R.styleable.PasswordView_circleColor, circleRadio);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        //if layout_width/layout_height is wrap_content, give it a exactly size
        if (wMode == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(DisplayUtil.dpToPixel(120), MeasureSpec.EXACTLY);
        }
        if (hMode == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(DisplayUtil.dpToPixel(50), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        itemWidth = getWidth() / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(dotColor);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 4; i++) {
            canvas.drawCircle(i * itemWidth + itemWidth / 2, getHeight() / 2, dotRadio, paint);
        }

        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < password.length(); i++) {
            canvas.drawCircle(i * itemWidth + itemWidth / 2, getHeight() / 2, circleRadio, paint);
        }
    }

    public void appendPassword(String number) {
        password.append(number);
        invalidate();
        if (password.length() == 4) {
            Log.d(TAG, "password: " + password);
            if (password.toString().equals("1234")) {
                Log.d(TAG, "密码正确");
                if (onResultListener != null) {
                    onResultListener.onCorrect();
                }
            } else {
                Log.d(TAG, "密码错误");
                if (onResultListener != null) {
                    onResultListener.onError();
                }
                password.delete(0,password.length());
                invalidate();
            }
        }
    }
}
