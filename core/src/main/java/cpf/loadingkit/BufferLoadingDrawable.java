package cpf.loadingkit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import androidx.annotation.NonNull;

/**
 * @author : cpf
 * @date : 12/30/20
 * email : cpf4263@gmail.com
 * description : 使用Bitmap做缓冲机制，提高性能
 */
public abstract class BufferLoadingDrawable extends LoadingDrawable {

    protected Bitmap mBitmap;

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        drawBitmap();
    }

    @Override
    public void start() {
        super.start();
        if (mBitmap == null || mBitmap.isRecycled()) {
            drawBitmap();
        }
    }

    @Override
    public void stop() {
        super.stop();
        releaseBitmap();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mBitmap == null || mBitmap.isRecycled()) {
            return;
        }
        onDraw(canvas);
    }

    private void drawBitmap() {
        releaseBitmap();
        if (mWidth <= 0 || mHeight <= 0) {
            return;
        }
        mBitmap = Bitmap.createBitmap((int) mWidth, (int) mHeight, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(mBitmap);
        onBitmapDraw(bitmapCanvas);
    }

    private void releaseBitmap() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    protected abstract void onBitmapDraw(@NonNull Canvas canvas);

}
