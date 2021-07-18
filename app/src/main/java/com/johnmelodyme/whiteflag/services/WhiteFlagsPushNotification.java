package com.johnmelodyme.whiteflag.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;

import java.util.Objects;

public class WhiteFlagsPushNotification extends FirebaseMessagingService
{
    public WhiteFlagsPushNotification()
    {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);

        FlagFunctions.log_output(
                Objects.requireNonNull(remoteMessage.getFrom()),
                0,
                LogLevel.DEBUG
        );

        FlagFunctions.log_output(String.valueOf(remoteMessage.getData()), 0, LogLevel.DEBUG);
    }

    @Override
    public void onNewToken(String s)
    {
        super.onNewToken(s);

        FlagFunctions.log_output(s, 0, LogLevel.DEBUG);
    }
}
