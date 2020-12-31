package cpf.loadingkit;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

/**
 * @author : cpf
 * @date : 12/29/20
 * email : cpf4263@gmail.com
 * description :
 */
public abstract class LoadingDrawable extends Drawable implements Animatable, ValueAnimator.AnimatorUpdateListener {

    protected Paint mPaint;
    protected float mWidth;
    protected float mHeight;
    protected float mCenterX;
    protected float mCenterY;
    protected ValueAnimator mValueAnimator;

    public LoadingDrawable() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mValueAnimator = createAnimator();
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mWidth = bounds.width();
        mHeight = bounds.height();
        mCenterX = mWidth / 2f;
        mCenterY = mHeight / 2f;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    protected abstract ValueAnimator createAnimator();

    /**
     * use onDraw
     */
    @Deprecated
    @Override
    public void draw(@NonNull Canvas canvas) {
        onDraw(canvas);
    }

    protected abstract void onDraw(@NonNull Canvas canvas);

    @Override
    public void start() {
        if (mValueAnimator != null && !mValueAnimator.isRunning()) {
            mValueAnimator.addUpdateListener(this);
            mValueAnimator.start();
        }
    }

    @Override
    public void stop() {
        if (mValueAnimator != null && mValueAnimator.isRunning()) {
            mValueAnimator.removeAllUpdateListeners();
            mValueAnimator.cancel();
        }
    }

    @Override
    public boolean isRunning() {
        return mValueAnimator != null && mValueAnimator.isRunning();
    }

    /**
     * 可设置渐变色
     *
     * @param color int
     */
    public void setColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    /**
     * 设置转一圈的时间
     *
     * @param duration 单位: ms
     */
    public void setDuration(int duration) {
        mValueAnimator.setDuration(duration);
    }

    /**
     * 设置动画插值器
     *
     * @param interpolator 默认：LinearInterpolator
     */
    public void setInterpolator(Interpolator interpolator) {
        mValueAnimator.setInterpolator(interpolator);
    }

    /**
     * 使用圆角
     *
     * @param useCorner 默认使用圆角
     */
    public void useCorner(boolean useCorner) {
        mPaint.setStrokeCap(useCorner ? Paint.Cap.ROUND : Paint.Cap.BUTT);
    }

    /**
     * 设置线宽
     *
     * @param width float
     */
    public void setStrokeWidth(float width) {
        mPaint.setStrokeWidth(width);
    }

    /**
     * 该方法可以解决setIndeterminateDrawable无效
     *
     * @param progressBar ProgressBar
     */
    public void setupWithProgressBar(ProgressBar progressBar) {
        Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
        if (indeterminateDrawable instanceof Animatable) {
            Animatable animatable = (Animatable) indeterminateDrawable;
            if (animatable.isRunning()) {
                animatable.stop();
            }
        }
        if (!ViewCompat.isAttachedToWindow(progressBar)) {
            progressBar.setIndeterminateDrawable(this);
            return;
        }
        Rect rect = new Rect();
        progressBar.getLocalVisibleRect(rect);
        setBounds(rect);
        progressBar.setIndeterminateDrawable(this);
        start();
    }
}
