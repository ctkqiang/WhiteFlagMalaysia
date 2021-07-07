package com.johnmelodyme.whiteflag.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.constants.Constants;
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.about:
            {
                FlagFunctions.alert_prompt(
                        getString(R.string.copyright),
                        getString(R.string.disclaimer),
                        this
                );

                break;
            }

            case R.id.support:
            {
                FlagFunctions.open_url(Constants.dev_profile_url, this);
                break;
            }

            case R.id.report:
            {
                FlagFunctions.email_to_dev(this);
                break;
            }

            default:
            {
                break;
            }
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }
}