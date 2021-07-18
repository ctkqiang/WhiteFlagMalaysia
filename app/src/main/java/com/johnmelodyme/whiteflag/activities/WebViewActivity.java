package com.johnmelodyme.whiteflag.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;

public class WebViewActivity extends AppCompatActivity
{
    public static final LogLevel LOG_LEVEL = LogLevel.DEBUG;
    public ProgressBar progressBar;
    public WebView webView;

    /**
     * @param bundle required for user interface rendering at
     *               beginning of the Instance.
     */
    public void render_user_interface_components(Bundle bundle)
    {
        FlagFunctions.log_output("render_user_interface_components/1", 0, LOG_LEVEL);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                set_progress_bar_visibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                set_progress_bar_visibility(View.GONE);

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error)
            {
                super.onReceivedError(view, request, error);
                set_progress_bar_visibility(View.GONE);
            }
        });

        /* Render WebView */
        FlagFunctions.open_url_in_app(WebViewActivity.this, bundle, webView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        /* Render Action Bar */
        FlagFunctions.render_action_bar(this, savedInstanceState);

        /* Render User Interface Components */
        render_user_interface_components(savedInstanceState);
    }

    private void set_progress_bar_visibility(int visibility)
    {
        if (progressBar != null)
        {
            FlagFunctions.log_output("set_progress_bar_visibility/1", 0, LOG_LEVEL);

            progressBar.setVisibility(visibility);
        }
    }
}