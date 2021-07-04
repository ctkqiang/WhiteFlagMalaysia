package com.johnmelodyme.whiteflag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;

public class HelpSeekerActivity extends AppCompatActivity
{
    public static LogLevel LEVEL = LogLevel.DEBUG;
    public EditText name;
    public EditText phoneNumber;
    public EditText address;
    public EditText description;

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
        setContentView(R.layout.activity_help_seeker);

        /* Render Action Bar */
        FlagFunctions.render_action_bar(this, savedInstanceState);

        /* Render Windows Colour */
        FlagFunctions.render_bottom_navigation(this);

        /* Render User Interface */
        render_user_interface(savedInstanceState);
    }

    /**
     * post_request -> posts users requests to the community for helps
     *
     * @param view Button INstances
     */
    public void post_request(View view)
    {
        FlagFunctions.log_output("post_request/1 ", 0, LEVEL);
    }
}