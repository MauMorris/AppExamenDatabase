package com.example.idscomercial.examen1;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.idscomercial.examen1.databinding.ActivityMainBinding;
import com.example.idscomercial.examen1.ui.EnrollmentValidacionActivity;
import com.example.idscomercial.examen1.ui.LeeCapturaActivity;
import com.example.idscomercial.examen1.ui.callbacks.FinishAnimationListener;
import com.example.idscomercial.examen1.ui.callbacks.MainActivityAnimation;
import com.example.idscomercial.examen1.vm.MainViewModel;

public class MainActivity extends AppCompatActivity implements FinishAnimationListener{
    //Cada LOG_TAG que aparezca en el codigo es para el taggeo de los flujos que recorre el usuario
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //tiempo antes de que entre en accion el hilo

    private Animation moveText;
    private Animation showAnim;
    private Handler mShowHandler;
    private MainActivityAnimation mMainAnimation;
    private Context mContext;
    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        mMainAnimation = new MainActivityAnimation(this);
        mShowHandler = new Handler();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setAnims();
        subscribeUI(mViewModel);
    }

    private void setAnims() {
        moveText = AnimationUtils.loadAnimation(mContext, R.anim.move_text);
        showAnim = AnimationUtils.loadAnimation(mContext, R.anim.show_view);
    }

    private void subscribeUI(MainViewModel mViewModel) {
        mViewModel.getWebLiveData().observe(this, aBoolean -> {
            Intent next;

            if(aBoolean){
                next = new Intent(mContext, LeeCapturaActivity.class);
            } else{
                next = new Intent(mContext, EnrollmentValidacionActivity.class);
            }

            startActivity(next);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        delayedIntro(getResources().getInteger(R.integer.intro_delay_milis));
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
        Log.d(LOG_TAG, " back pressed: elimina " + LOG_TAG);
    }

    private void delayedIntro(int delay) {
        mBinding.lottieAnim.setVisibility(View.INVISIBLE);
        mBinding.splashTextView.startAnimation(moveText);

        try{
            Log.d(LOG_TAG, " db: inicio de hilo de animacion");

            mShowHandler.removeCallbacks(mIntroRunnable);
            mShowHandler.removeCallbacks(mShowRunnable);

            mShowHandler.postDelayed(mIntroRunnable, delay);
        } catch(Exception e){
            Log.d(LOG_TAG, " db: no se pudo iniciar la animacion");
        }
    }

    private final Runnable mIntroRunnable = new Runnable() {
        @Override
        public void run() {
            mBinding.lottieAnim.setVisibility(View.VISIBLE);
            mBinding.lottieAnim.setAnimation(showAnim);
            Log.d(LOG_TAG, " db: inicio de consulta de data");

            try{

                mShowHandler.removeCallbacks(mIntroRunnable);
                mShowHandler.removeCallbacks(mShowRunnable);

                mShowHandler.postDelayed(mShowRunnable,
                        getResources().getInteger(R.integer.next_layout_delay_milis));
            } catch(Exception e){
                Log.d(LOG_TAG, " db: no se pudo crear la base de datos");
            }
        }
    };

    private final Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            mMainAnimation.finishTask();
        }
    };

    @Override
    public void finishedTime() {
        mViewModel.getActivateEnrollmentStatus(mContext);
    }
}