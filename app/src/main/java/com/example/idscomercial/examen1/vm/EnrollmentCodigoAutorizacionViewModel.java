package com.example.idscomercial.examen1.vm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.idscomercial.examen1.device.notifications.NotificationUtils;

public class EnrollmentCodigoAutorizacionViewModel extends AndroidViewModel {
    public EnrollmentCodigoAutorizacionViewModel(@NonNull Application application) {
        super(application);
    }

    public void requestValidCode(Context context){
        NotificationUtils.setNotificationForValidCode(context);
    }
}
