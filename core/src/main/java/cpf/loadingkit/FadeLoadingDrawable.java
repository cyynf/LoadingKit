package cpf.loadingkit;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.view.animation.LinearInterpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * @author : cpf
 * @date : 12/29/20
 * email : cpf4263@gmail.com
 * description : 透明渐变
 */
public class FadeLoadingDrawable extends LoadingDrawable {

    protected int mAngle;
    protected int mColor;
    protected final RectF mRectF = new RectF();

    public FadeLoadingDrawable() {
        setInterpolator(new LinearInterpolator());
        mPaint.setStyle(Paint.Style.STROKE);
        setColor(Color.BLACK);
        setDuration(1500);
        useCorner(true);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if (mPaint.getStrokeWidth() <= 0) {
            setStrokeWidth(mWidth / 8);
        }

        mRectF.set(getBounds());
        float offset = mPaint.getStrokeWidth() / 2;
        mRectF.inset(offset, offset);

        SweepGradient sweepGradient = new SweepGradient(mCenterX, mCenterY,
                new int[]{0, mColor, mColor}, new float[]{0, 0.5f, 1});
        mPaint.setShader(sweepGradient);
    }

    /**
     * 可设置渐变色
     *
     * @param color int
     */
    @Override
    public void setColor(@ColorInt int color) {
        mColor = color;
    }

    @Override
    protected ValueAnimator createAnimator() {
        return ValueAnimator.ofInt(0, 360);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mAngle = (Integer) animation.getAnimatedValue();
        invalidateSelf();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.rotate(mAngle, mCenterX, mCenterY);
        canvas.drawArc(mRectF, 30, 270, false, mPaint);
        canvas.restore();
    }

}
