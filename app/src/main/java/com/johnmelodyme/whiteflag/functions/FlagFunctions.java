package com.johnmelodyme.whiteflag.functions;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;

import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.constants.Constants;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class FlagFunctions extends Constants
{
    public static String TAG = TAG_NAME;

    public static void log_output(@NonNull String string, int ok, LogLevel logLevel)
    {
        switch (logLevel)
        {
            case DEBUG:
            case INFO:
            case ERROR:
            case VERBOSE:
            case WARN:
            {
                if (ok == 0)
                {
                    Log.d(TAG, logLevel.toString() + " {:ok " + string + "}");
                }
                else
                {
                    Log.d(TAG, logLevel.toString() + " {:error " + string + "}");
                }
                break;
            }
            default:
            {
                Log.d(TAG, " {:unknown " + string + "}");
                break;
            }
        }

    }

    /**
     * @param activity The User Current Instances of the Activity
     * @param bundle   The Instance of the class rendered
     */
    public static void render_action_bar(AppCompatActivity activity, Bundle bundle)
    {
        log_output("render_action_bar/2", 0, LogLevel.DEBUG);

        ActionBar actionbar = activity.getSupportActionBar();

        if (actionbar != null)
        {
            actionbar.setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.black)));
            activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            activity.getSupportActionBar().setCustomView(R.layout.action_bar);
        }
    }

    /**
     * @param activity Get Current UI Instances and renders color to bottom Navigation
     */
    public static void render_bottom_navigation(AppCompatActivity activity)
    {
        log_output("render_bottom_navigation/1", 0, LogLevel.DEBUG);

        activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.black));
    }

    /**
     * @param context Current instance of the application are required for
     *                getting user permission either accepted or denied.
     */
    public static void get_user_permission(@NonNull Context context)
    {
        LogLevel permission = LogLevel.DEBUG;

        Dexter.withContext(context).withPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE
        ).withListener(new MultiplePermissionsListener()
        {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport)
            {
                log_output("get_user_permission/1", 0, permission);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> l,
                                                           PermissionToken pt)
            {

                if (pt != null)
                {
                    log_output("get_user_permission/1", 0, permission);
                }

            }
        }).onSameThread().check();
    }

    /**
     * @param context   required Context for register
     * @param classname required for routing {@link #route_to(Context, Class)}
     */
    public static void route_to(Context context, Class<?> classname)
    {
        log_output("route_to/2, to:" + classname.getSimpleName(), 0, LogLevel.DEBUG);

        Intent navigation = new Intent(context, classname);
        context.startActivity(navigation);
    }

    /**
     * @param number  Required Helper seeker's phone number to Initiate phone calls
     * @param context Current instance of the application
     */
    public static void call_user(String number, Context context)
    {
        log_output("call_user/2", 0, LogLevel.DEBUG);

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    /**
     * @param context Current Instances
     * @return String ~> Expect return MYS
     */
    public static String get_locale(@NonNull Context context)
    {
        log_output("get_locale/1", 0, LogLevel.DEBUG);

        String locale = context.getResources().getConfiguration().locale.getDisplayCountry();


        if (!locale.contains("MYS"))
        {
            return "NOT MALAYSIAN";
        }

        log_output(locale, 0, LogLevel.DEBUG);

        return locale.toUpperCase();
    }

    /**
     * @param message Required By Developer
     * @param context Current Instances
     */
    public static void show_toast(String message, Context context)
    {
        log_output("show_toast/2", 0, LogLevel.DEBUG);

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * @param context Current Instances
     * @return String Network Name
     */
    @SuppressLint("ServiceCast")
    public static String get_carrier(Context context)
    {
        log_output("get_carrier/1", 0, LogLevel.DEBUG);

        TelephonyManager manager;
        manager = (TelephonyManager) context.getSystemService(Context.TELECOM_SERVICE);

        log_output(manager.getNetworkOperatorName(), 0, LogLevel.INFO);

        return manager.getNetworkOperatorName();
    }

    /**
     * @param url     Get User Url.
     * @param context User Instances
     */
    public static void open_url(String url, Context context)
    {
        log_output("open_url/2", 0, LogLevel.DEBUG);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    /**
     * @param context Current Instances
     */
    @SuppressLint("IntentReset")
    public static void email_to_dev(Context context)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"johnmelody@dingatlk.com"});

        log_output("email_to_dev/1", 0, LogLevel.DEBUG);

        context.startActivity(Intent.createChooser(intent, "Send Email To Developer"));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void alert_prompt(String title, String message, Context context)
    {
        log_output("alert_prompt/3", 0, LogLevel.DEBUG);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setCancelable(true).setNegativeButton(
                context.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                }
        ).setTitle(title).show();
    }

    /**
     * @param context The Current Instance of the Application
     * @return String {@code IPv4 address of the current user}
     */
    @SuppressLint("WifiManagerPotentialLeak")
    public static String get_user_current_ip(Context context)
    {
        log_output("get_user_current_ip/1", 0, LogLevel.DEBUG);

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }


    public static String decrypt_message(String input)
    {
        try
        {
            return URLDecoder.decode(input, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
