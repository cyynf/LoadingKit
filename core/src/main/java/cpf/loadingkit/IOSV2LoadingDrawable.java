package cpf.loadingkit;

import android.animation.ValueAnimator;

/**
 * @author : cpf
 * @date : 12/29/20
 * email : cpf4263@gmail.com
 * description : 仿IOS
 */
public class IOSV2LoadingDrawable extends IOSLoadingDrawable {

    public IOSV2LoadingDrawable() {
        setDuration(2500);
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


}
