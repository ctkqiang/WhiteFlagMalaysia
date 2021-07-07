package com.johnmelodyme.whiteflag.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.constants.Constants;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;
import com.johnmelodyme.whiteflag.services.PostService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HelpSeekerActivity extends AppCompatActivity
{
    public static LogLevel LEVEL = LogLevel.DEBUG;
    public EditText name;
    public EditText phoneNumber;
    public EditText address;
    public EditText description;
    public Button post_request;

    /* Render User Interface */
    public void render_user_interface(Bundle bundle)
    {
        FlagFunctions.log_output("render_user_interface/1", 0, LEVEL);

        name = (EditText) findViewById(R.id.name);
        phoneNumber = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        description = (EditText) findViewById(R.id.description);

        post_request = (Button) findViewById(R.id.post_request);
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
     * @param view getCurrent View components
     */
    public void post_request(View view)
    {
        RestAdapter restAdapter;

        FlagFunctions.log_output("post_request/1 ", 0, LEVEL);

        String user_name = name.getText().toString();
        String user_phone = phoneNumber.getText().toString();
        String user_home = address.getText().toString();
        String user_posts = description.getText().toString();

        if (!(TextUtils.isEmpty(user_name)) && !(TextUtils.isEmpty(user_phone)) && !(TextUtils.isEmpty(user_home)))
        {
            restAdapter = new Builder().setEndpoint(Constants.url).build();

            PostService postService = restAdapter.create(PostService.class);
            finish();

            postService.insertUser(
                    user_name, user_phone, user_home, user_posts, new Callback<Response>()
                    {

                        /**
                         * Successful HTTP response.
                         *
                         * @param response
                         * @param response2
                         */
                        @Override
                        public void success(Response response, Response response2)
                        {

                            //On success we will read the server's output using
                            // bufferedreader
                            //Creating a bufferedreader object
                            BufferedReader reader = null;

                            //An string to store output from the server
                            String output = "";

                            try
                            {
                                //Initializing buffered reader
                                reader =
                                        new BufferedReader(new InputStreamReader(response.getBody().in()));

                                //Reading the output in the string
                                output = reader.readLine();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                            FlagFunctions.log_output(output, 0, LEVEL);
                            FlagFunctions.log_output(response.getUrl(), 0, LEVEL);

                            if (response.getStatus() == 200)
                            {

                                FlagFunctions.log_output(
                                        "post_service/1 return -> " + String.valueOf(response.getStatus()),
                                        0,
                                        LEVEL
                                );

                                FlagFunctions.show_toast(getString(R.string.return200), HelpSeekerActivity.this);



                                FlagFunctions.show_toast(getString(R.string.success_message), HelpSeekerActivity.this);
                            }
                            else
                            {

                                FlagFunctions.log_output(
                                        "post_service/1 return -> " + String.valueOf(response.getStatus()),
                                        1,
                                        LEVEL
                                );

                                FlagFunctions.show_toast(getString(R.string.return400), HelpSeekerActivity.this);


                            }
                        }

                        /**
                         * Unsuccessful HTTP response due to network failure, non-2XX
                         * status code, or unexpected
                         * exception.
                         *
                         * @param error
                         */
                        @Override
                        public void failure(RetrofitError error)
                        {
                            finish();

                            FlagFunctions.show_toast(
                                    error.toString(),
                                    HelpSeekerActivity.this
                            );
                        }
                    }
            );
        }
    }
}