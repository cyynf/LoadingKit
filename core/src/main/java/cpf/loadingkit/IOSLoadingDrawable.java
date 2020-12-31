package cpf.loadingkit;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

/**
 * @author : cpf
 * @date : 12/29/20
 * email : cpf4263@gmail.com
 * description : 仿IOS
 */
public class IOSLoadingDrawable extends BufferLoadingDrawable {

    protected int mAngle;
    protected Paint paint;

    public IOSLoadingDrawable() {
        setInterpolator(new LinearInterpolator());
        mPaint.setStyle(Paint.Style.STROKE);
        paint = new Paint();
        paint.setAntiAlias(true);
        setColor(Color.BLACK);
        setDuration(1000);
        useCorner(true);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        if (mPaint.getStrokeWidth() <= 0) {
            setStrokeWidth(bounds.width() / 12f);
        }
        super.onBoundsChange(bounds);
    }

    @Override
    protected ValueAnimator createAnimator() {
        return ValueAnimator.ofInt(0, 12);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mAngle = (Integer) animation.getAnimatedValue() * 30;
        invalidateSelf();
    }

    @Override
    protected void onBitmapDraw(@NonNull Canvas canvas) {
        float offset = mPaint.getStrokeWidth() / 2;
        for (int i = 1; i <= 12; i++) {
            canvas.save();
            canvas.rotate(i * 30, mCenterX, mCenterY);
            mPaint.setAlpha(64 + 191 * i / 12);
            canvas.drawLine(offset, mCenterY, mCenterX / 2, mCenterY, mPaint);
            canvas.restore();
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.rotate(mAngle, mCenterX, mCenterY);
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        canvas.restore();
    }
}
