package com.example.idscomercial.examen1.impl;

import com.example.idscomercial.examen1.callbacks.MyIntentListener;

/*
 * Created by mauriciogodinez on 18/12/17.
 */

public class MyIntentImpl {
    private MyIntentListener listener;

    public MyIntentImpl(){
        this.listener = null;
    }

    public void start(){
        if(listener != null){
            listener.finishedTime();
        }
    }

    public void setOnStartListener(MyIntentListener listener){
        this.listener = listener;
    }
}
