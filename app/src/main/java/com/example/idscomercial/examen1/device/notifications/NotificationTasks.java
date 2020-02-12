package com.example.idscomercial.examen1.device.notifications;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationTasks {
    public static final String ACTION_APPLY_CODE = "apply_code";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String EXTRA_CODE = "extra_code";

    public static void executeTask(Context context, String action, String extra) {
        if (ACTION_APPLY_CODE.equals(action)) {
            automaticApplyCode(context, extra);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        }
    }

    private static void automaticApplyCode(Context context, String extra) {
        Intent intent = new Intent();
        intent.setAction("com.CUSTOM_INTENT");
        Log.d("MY_REQUEST", "this is the data " + extra);
        intent.putExtra(NotificationTasks.EXTRA_CODE, extra);

        context.sendBroadcast(intent);

        NotificationUtils.clearAllNotifications(context);
    }
}