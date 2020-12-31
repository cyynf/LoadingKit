package cpf.loadingkit;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

/**
 * @author : cpf
 * @date : 12/30/20
 * email : cpf4263@gmail.com
 * description :
 */
public class FadeScaleLoadingDrawable extends BufferLoadingDrawable {

    protected int mAngle;
    protected float mRadius;
    protected Paint paint;

    public FadeScaleLoadingDrawable() {
        setInterpolator(new LinearInterpolator());
        setDuration(1000);
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        if (mRadius <= 0) {
            mRadius = bounds.width() / 24f;
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
        for (int i = 1; i <= 12; i++) {
            canvas.save();
            canvas.rotate(i * 30, mCenterX, mCenterY);
            float radius = calcRadius(i);
            mPaint.setAlpha(104 + 151 * i / 12);
            canvas.drawCircle(mRadius * 2, mCenterY, radius, mPaint);
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

    private float calcRadius(int value) {
        if (value <= 6) {
            return mRadius;
        }
        return value * mRadius / 6f;
    }
}
