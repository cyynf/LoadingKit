package cpf.loadingkit.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import cpf.loadingkit.FadeLoadingDrawable;
import cpf.loadingkit.FadeScaleLoadingDrawable;
import cpf.loadingkit.IOSLoadingDrawable;
import cpf.loadingkit.IOSV2LoadingDrawable;
import cpf.loadingkit.LoadingDrawable;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
    }

    public void fadeLoadingDrawable(View view) {
        LoadingDrawable drawable = new FadeLoadingDrawable();
        drawable.setupWithProgressBar(progressBar);
    }

    public void fadeScaleLoadingDrawable(View view) {
        LoadingDrawable drawable = new FadeScaleLoadingDrawable();
        drawable.setupWithProgressBar(progressBar);
    }

    public void iosDrawable(View view) {
        LoadingDrawable drawable = new IOSLoadingDrawable();
        drawable.setupWithProgressBar(progressBar);
    }

    public void iosV2Drawable(View view) {
        LoadingDrawable drawable = new IOSV2LoadingDrawable();
        drawable.setupWithProgressBar(progressBar);
    }

}