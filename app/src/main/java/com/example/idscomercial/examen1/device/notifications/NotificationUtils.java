package com.example.idscomercial.examen1.device.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Action;
import androidx.core.content.ContextCompat;

import com.example.idscomercial.examen1.R;

public class NotificationUtils {
    private static final int VALIDACION_NOTIFICATION_ID = 1111;

    private static final String VALIDACION_NOTIFICATION_CHANNEL_ID = "validacion_notification_channel";

    private static final int VALIDACION_PENDING_INTENT_ID = 3417;
    private static final int ACTION_DO_PENDING_INTENT_ID = 1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_local_drink_black_24px);
        return largeIcon;
    }

    public static void setNotificationForValidCode(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel mChannel = new NotificationChannel(
                    VALIDACION_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context,
                VALIDACION_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_local_drink_black_24px)
                .setLargeIcon(largeIcon(context))
                .setContentTitle("Este es tu codigo de autorización")
                .setContentText("código: 112233")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("código: 112233"))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context, 112233))
                .addAction(applyCodeAction(context, 112233))
                .addAction(ignoreCodeAction(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(VALIDACION_NOTIFICATION_ID, notificationBuilder.build());
    }

    private static Action ignoreCodeAction(Context context) {
        Intent ignoreReminderIntent = new Intent(context, NotificationIntentService.class);
        ignoreReminderIntent.setAction(NotificationTasks.ACTION_DISMISS_NOTIFICATION);

        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        return new Action(R.drawable.ic_cancel_black_24px,
                context.getString(R.string.not_apply_text),
                ignoreReminderPendingIntent);
    }

    private static Action applyCodeAction(Context context, int code) {
        Intent applyCodeIntent = new Intent(context, NotificationIntentService.class);
        applyCodeIntent.setAction(NotificationTasks.ACTION_APPLY_CODE);
        applyCodeIntent.putExtra(NotificationTasks.EXTRA_CODE, code);

        PendingIntent setCodePendingIntent = PendingIntent.getService(
                context,
                ACTION_DO_PENDING_INTENT_ID,
                applyCodeIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        return new Action(R.drawable.ic_local_drink_black_24px,
                context.getString(R.string.apply_code_text),
                setCodePendingIntent);
    }

    private static PendingIntent contentIntent(Context context, int code) {
        Intent applyCodeIntent = new Intent(context, NotificationIntentService.class);
        applyCodeIntent.setAction(NotificationTasks.ACTION_APPLY_CODE);
        applyCodeIntent.putExtra(NotificationTasks.EXTRA_CODE, code);

        return PendingIntent.getService(
                context,
                VALIDACION_PENDING_INTENT_ID,
                applyCodeIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}