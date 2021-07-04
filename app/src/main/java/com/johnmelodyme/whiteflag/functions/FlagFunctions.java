package com.johnmelodyme.whiteflag.functions;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
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
                    Log.d(TAG, logLevel + " {:ok " + string + "}");
                }
                else
                {
                    Log.d(TAG, logLevel + " {:error " + string + "}");
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
                Manifest.permission.ACCESS_FINE_LOCATION
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
}
