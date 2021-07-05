package com.johnmelodyme.whiteflag.services;

import com.johnmelodyme.whiteflag.constants.LogLevel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPHandler
{
    public static LogLevel LEVEL = LogLevel.DEBUG;

    public HTTPHandler()
    {
    }

    public String make_service_call(String base_url)
    {
        String response = null;

        try
        {
            URL url = new URL(base_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");

            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            response = convert_stream_to_string(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return response;
    }

    public String convert_stream_to_string(InputStream inputStream)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;

        try
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line).append('\n');
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
