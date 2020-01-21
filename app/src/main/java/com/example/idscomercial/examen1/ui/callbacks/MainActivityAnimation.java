package com.example.idscomercial.examen1.ui.callbacks;

import com.example.idscomercial.examen1.ui.callbacks.FinishAnimationListener;

/*
 * Created by mauriciogodinez on 18/12/17.
 */

public class MainActivityAnimation {
    private FinishAnimationListener listener;

    public MainActivityAnimation(FinishAnimationListener listener){
        this.listener = listener;
    }

    public void finishTask(){
        if(listener != null){
            listener.finishedTime();
        }
    }
}