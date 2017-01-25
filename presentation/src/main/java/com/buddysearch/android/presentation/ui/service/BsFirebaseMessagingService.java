package com.buddysearch.android.presentation.ui.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.buddysearch.android.data.store.firebase.FirebaseMessageEntityStore;
import com.buddysearch.android.data.util.StringUtil;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.library.presentation.DefaultObserver;
import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.presentation.App;
import com.buddysearch.android.presentation.di.component.ViewComponent;
import com.buddysearch.android.presentation.di.module.ViewModule;
import com.buddysearch.android.presentation.mvp.presenter.DialogPresenter;
import com.buddysearch.android.presentation.ui.activity.DialogActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import javax.inject.Inject;

public class BsFirebaseMessagingService extends FirebaseMessagingService {

    @Inject
    GetUser getUser;

    private ViewComponent viewComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        viewComponent = ((App) getApplicationContext())
                .getAppComponent()
                .plus(new ViewModule(new ViewImpl(this) {
                }));
        viewComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //getUser.dispose(); do not dispose to receive result event when server was destroyed
        viewComponent = null;
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        if (data == null) {
            return;
        }
        String senderId = data.get(FirebaseMessageEntityStore.KEY_FCM_SENDER_ID);
        if (TextUtils.isEmpty(senderId)) {
            return;
        }
        String text = data.get(FirebaseMessageEntityStore.KEY_FCM_TEXT);
        sendNotification(senderId, text);
        getUser.execute(senderId, new DefaultObserver<UserDto>(null) {
            @Override
            public void onNext(UserDto userDto) {
                super.onNext(userDto);
                updateNotification(senderId,
                        StringUtil.concatLinearly(" ", userDto.getFirstName(), userDto.getLastName()),
                        text, false);
            }
        });

    }

    private void sendNotification(String senderId, String message) {
        updateNotification(senderId, null, message, true);
    }

    private void updateNotification(String senderId, String senderFullName, String message, boolean needSound) {
        if (DialogPresenter.getCurrentPeerId() != null) {
            return;
        }
        Intent intent = new Intent(this, DialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(DialogActivity.KEY_PEER_ID, senderId);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(senderFullName == null ? getString(com.buddysearch.android.library.R.string.loading) : senderFullName)
                .setContentText(message)
                .setSmallIcon(com.buddysearch.android.data.R.drawable.ic_notification_message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        if (needSound) {
            notificationBuilder.setSound(defaultSoundUri);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(senderId.hashCode(), notificationBuilder.build());
    }
}
