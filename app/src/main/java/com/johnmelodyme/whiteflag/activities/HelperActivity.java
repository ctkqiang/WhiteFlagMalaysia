package com.johnmelodyme.whiteflag.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.components.FlagsAdapter;
import com.johnmelodyme.whiteflag.constants.Constants;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;
import com.johnmelodyme.whiteflag.model.WhiteFlagsGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class HelperActivity extends AppCompatActivity
{
    public static LogLevel LEVEL = LogLevel.DEBUG;
    public static String base_url = Constants.get_data;
    public ArrayList<WhiteFlagsGet> whiteFlagList;
    public FlagsAdapter adapter;
    public ProgressDialog dialog;
    public ProgressDialog d;
    public ListView listView;
    public EditText searchText;
    public String search_char;

    /* Render User Interface */
    public void render_user_interface(Bundle bundle)
    {
        FlagFunctions.log_output("render_user_interface/1", 0, LEVEL);

        whiteFlagList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.recycler_view);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) (parent, view, position
                , id) ->
        {
            WhiteFlagsGet whiteFlags = (WhiteFlagsGet) whiteFlagList.get(position);

            FlagFunctions.log_output(
                    whiteFlags.getUserName() + " " + whiteFlags.getPhoneNumber(),
                    0, LEVEL
            );
        });

        searchText = (EditText) findViewById(R.id.search);
        searchText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                System.out.println(s);

                search_char = String.valueOf(s);

                whiteFlagList.clear();

            }

            @Override
            public void afterTextChanged(Editable arg0)
            {
            }
        });

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
        setContentView(R.layout.activity_helper);

        /* Render Action Bar */
        FlagFunctions.render_action_bar(this, savedInstanceState);

        /* Render Windows Colour */
        FlagFunctions.render_bottom_navigation(this);

        /* Render User Interface */
        render_user_interface(savedInstanceState);

        /* Get Data*/
        new GetData().execute();
    }


    /* Async task class to get JSON by making HTTP call */
    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<String, String, String>
    {
        /**
         * Runs on the UI thread before {@link #doInBackground}.
         * Invoked directly by {@link #execute} or {@link #executeOnExecutor}.
         * The default version does nothing.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            dialog = new ProgressDialog(HelperActivity.this, R.style.AppCompatAlertDialogStyle);
            dialog.setMessage(getResources().getString(R.string.loading));
            dialog.show();

        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... params)
        {
            String result = null;

            whiteFlagList.clear();

            try
            {
                URL url = new URL(base_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    InputStreamReader inputStreamReader =
                            new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null)
                    {
                        stringBuilder.append(temp);
                    }

                    result = stringBuilder.toString();
                }
                else
                {
                    result = "Error";
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return result;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.
         * To better support testing frameworks, it is recommended that this be
         * written to tolerate direct execution as part of the execute() call.
         * The default version does nothing.</p>
         *
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param s The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         */
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            try
            {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("WHITE_FLAG");

                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);

                    String name = jsonObject.getString("USER_NAME");
                    String phone = jsonObject.getString("PHONE_NUMBER");
                    String home = jsonObject.getString("HOME_ADDRESS");
                    String description = jsonObject.getString("DESCRIPTION");
                    int status = jsonObject.getInt("STATUS");
                    String date = jsonObject.getString("CREATED_AT");

                    WhiteFlagsGet whiteFlags = new WhiteFlagsGet();
                    whiteFlags.setUserName(name);
                    whiteFlags.setPhoneNumber(phone);
                    whiteFlags.setHomeAddress(home);
                    whiteFlags.setDescription(description);
                    whiteFlags.setStatus(status);
                    whiteFlags.setCreatedAt(date);

                    whiteFlagList.add(whiteFlags);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            adapter = new FlagsAdapter(HelperActivity.this, whiteFlagList);
            listView.setAdapter(adapter);
            dialog.dismiss();
        }
    }

    /* Async task class to get JSON by making HTTP call */
    @SuppressLint("StaticFieldLeak")
    public class SearchData extends AsyncTask<String, String, String>
    {
        /**
         * Runs on the UI thread before {@link #doInBackground}.
         * Invoked directly by {@link #execute} or {@link #executeOnExecutor}.
         * The default version does nothing.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            d = new ProgressDialog(HelperActivity.this, R.style.AppCompatAlertDialogStyle);
            d.setMessage(getResources().getString(R.string.search_msg));
            d.show();
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... params)
        {
            String result = null;
            d.dismiss();

            try
            {
                URL url =
                        new URL(Constants.url + Constants.get_search_data + "?keyword=" + search_char);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    InputStreamReader inputStreamReader =
                            new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null)
                    {
                        stringBuilder.append(temp);
                    }

                    result = stringBuilder.toString();
                }
                else
                {
                    result = "Error";
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return result;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.
         * To better support testing frameworks, it is recommended that this be
         * written to tolerate direct execution as part of the execute() call.
         * The default version does nothing.</p>
         *
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param s The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         */
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            whiteFlagList.clear();
            d.dismiss();

            try
            {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("WHITE_FLAG");

                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);

                    String name = jsonObject.getString("USER_NAME");
                    String phone = jsonObject.getString("PHONE_NUMBER");
                    String home = jsonObject.getString("HOME_ADDRESS");
                    String description = jsonObject.getString("DESCRIPTION");
                    int status = jsonObject.getInt("STATUS");
                    String date = jsonObject.getString("CREATED_AT");

                    WhiteFlagsGet whiteFlags = new WhiteFlagsGet();
                    whiteFlags.setUserName(name);
                    whiteFlags.setPhoneNumber(phone);
                    whiteFlags.setHomeAddress(home);
                    whiteFlags.setDescription(description);
                    whiteFlags.setStatus(status);
                    whiteFlags.setCreatedAt(date);

                    whiteFlagList.add(whiteFlags);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            adapter = new FlagsAdapter(HelperActivity.this, whiteFlagList);
            listView.setAdapter(adapter);
            d.dismiss();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
        {
            whiteFlagList.clear();
            new SearchData().execute();
        }

        return super.dispatchKeyEvent(event);
    }

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        searchText.getText().clear();
    }
}