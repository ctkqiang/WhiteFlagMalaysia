package com.johnmelodyme.whiteflag.services;

import android.content.Context;

import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;
import com.onesignal.OneSignal;

public class PushNotificationService
{
    public static final String API = "462c9fdc-d9ea-4642-b9ca-f3df0960ec82";

    public static void get_service(Context context)
    {
        FlagFunctions.log_output("get_service/1", 0, LogLevel.DEBUG);
        
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(context);
        OneSignal.setAppId(API);
    }
}
