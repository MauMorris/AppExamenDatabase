package com.example.idscomercial.examen1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.idscomercial.examen1.data.DatabaseHelper;
import com.example.idscomercial.examen1.ui.LeeCapturaActivity;

public class MainActivity extends AppCompatActivity {
    //Cada LOG_TAG que aparezca en el codigo es para el taggeo de los flujos que recorre el usuario
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //tiempo antes de que entre en accion el hilo
    private static final int NEXT_LAYOUT_DELAY_MILLIS = 1500;
    private Handler mShowHandler;
    //instancia del helper de base de datos
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mShowHandler = new Handler();
        delayedShow(NEXT_LAYOUT_DELAY_MILLIS);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mShowHandler.removeCallbacks(mShowRunnable);
    }

    private void delayedShow(int delay){
        try{
            myDb = new DatabaseHelper(this);
            Log.d(LOG_TAG, " db: base de datos creada");

            mShowHandler.removeCallbacks(mShowRunnable);
            mShowHandler.postDelayed(mShowRunnable, delay);
        } catch(Exception e){
            Log.d(LOG_TAG, " db: no se pudo crear la base de datos");
        }
    }

    private final Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            Intent next = new Intent(getApplicationContext(), LeeCapturaActivity.class);

            startActivity(next);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mShowHandler.removeCallbacks(mShowRunnable);
        Log.d(LOG_TAG, " db: presiona back: elimina " + LOG_TAG);
    }
}