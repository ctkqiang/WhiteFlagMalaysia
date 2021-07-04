package com.johnmelodyme.whiteflag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;


public class DecisionActivity extends AppCompatActivity
{
    public static LogLevel LEVEL = LogLevel.DEBUG;

    /* Render User Interface */
    public void render_user_interface(Bundle bundle)
    {
        FlagFunctions.log_output("render_user_interface/1", 0, LEVEL);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FlagFunctions.log_output("init_application/1", 0, LEVEL);

        /* Request User Permission */
        FlagFunctions.get_user_permission(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);

        /* Render Action Bar */
        FlagFunctions.render_action_bar(this, savedInstanceState);

        /* Render Windows Colour */
        FlagFunctions.render_bottom_navigation(this);

        /* Render User Interface */
        render_user_interface(savedInstanceState);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClicked(View view)
    {
        Button clicked_button = (Button) view;

        switch (clicked_button.getId())
        {
            case R.id.helper:
            {
                FlagFunctions.route_to(this, HelperActivity.class);
                break;
            }

            case R.id.help_seek:
            {
                FlagFunctions.route_to(this, HelpSeekerActivity.class);
                break;
            }

            default:
            {
                break;
            }
        }
    }
}