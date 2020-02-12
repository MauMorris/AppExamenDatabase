package com.example.idscomercial.examen1.device.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.idscomercial.examen1.ui.EnrollmentCodigoAutorizacionCallbackFromReceiver;

public class NotificationReceiver extends BroadcastReceiver {
    EnrollmentCodigoAutorizacionCallbackFromReceiver mReturnDataFromReceiver;

    public NotificationReceiver(EnrollmentCodigoAutorizacionCallbackFromReceiver codigoAutorizacionEt) {
    this.mReturnDataFromReceiver = codigoAutorizacionEt;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.CUSTOM_INTENT")){
            String data = intent.getStringExtra(NotificationTasks.EXTRA_CODE);

            mReturnDataFromReceiver.returnData(data);
        }
    }
}