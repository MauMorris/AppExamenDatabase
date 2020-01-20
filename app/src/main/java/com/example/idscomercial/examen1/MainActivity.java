package com.example.idscomercial.examen1;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.idscomercial.examen1.databinding.ActivityMainBinding;
import com.example.idscomercial.examen1.ui.callbacks.FinishAnimationListener;
import com.example.idscomercial.examen1.ui.callbacks.MainActivityAnimation;
import com.example.idscomercial.examen1.ui.LeeCapturaActivity;

public class MainActivity extends AppCompatActivity implements FinishAnimationListener{
    //Cada LOG_TAG que aparezca en el codigo es para el taggeo de los flujos que recorre el usuario
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //tiempo antes de que entre en accion el hilo
    private static final int NEXT_LAYOUT_DELAY_MILLIS = 2500;
    private static final int INTRO_DELAY_MILIS = 600;
    private Handler mShowHandler;
    private MainActivityAnimation mMainAnimation;
    private Context mContext;
    private ActivityMainBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mContext = MainActivity.this;
        mMainAnimation = new MainActivityAnimation(this);
        mShowHandler = new Handler();

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        delayedIntro(INTRO_DELAY_MILIS);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mShowHandler.removeCallbacks(mIntroRunnable);
        mShowHandler.removeCallbacks(mShowRunnable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mShowHandler.removeCallbacks(mIntroRunnable);
        mShowHandler.removeCallbacks(mShowRunnable);
        Log.d(LOG_TAG, " db: presiona back: elimina " + LOG_TAG);
    }

    private void delayedIntro(int delay) {
        try{
            Log.d(LOG_TAG, " db: base de datos creada");
            mDataBinding.loadingIndicatorProgressBar.setVisibility(View.INVISIBLE);

            mShowHandler.removeCallbacks(mIntroRunnable);
            mShowHandler.removeCallbacks(mShowRunnable);

            mShowHandler.postDelayed(mIntroRunnable, delay);
        } catch(Exception e){
            Log.d(LOG_TAG, " db: no se pudo crear la base de datos");
        }
    }

    private final Runnable mIntroRunnable = new Runnable() {
        @Override
        public void run() {
            mDataBinding.loadingIndicatorProgressBar.setVisibility(View.VISIBLE);
            delayedShow(NEXT_LAYOUT_DELAY_MILLIS);
        }
    };

    private void delayedShow(int delay){
        try{
            Log.d(LOG_TAG, " db: base de datos creada");

            mShowHandler.removeCallbacks(mIntroRunnable);
            mShowHandler.removeCallbacks(mShowRunnable);
            mShowHandler.postDelayed(mShowRunnable, delay);
        } catch(Exception e){
            Log.d(LOG_TAG, " db: no se pudo crear la base de datos");
        }
    }

    private final Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            mMainAnimation.finishTask();
        }
    };

    @Override
    public void finishedTime() {
        Intent next = new Intent(mContext, LeeCapturaActivity.class);

        startActivity(next);
        finish();
    }
}