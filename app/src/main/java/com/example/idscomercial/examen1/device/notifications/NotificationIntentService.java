package com.example.idscomercial.examen1.device.notifications;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class NotificationIntentService extends IntentService {
    public NotificationIntentService() {
        super("NotificationValidateCodeIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            int extra = intent.getIntExtra(NotificationTasks.EXTRA_CODE, 0);
            NotificationTasks.executeTask(this, action, Integer.toString(extra));
        }
    }
}