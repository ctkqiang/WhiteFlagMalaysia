package com.johnmelodyme.whiteflag.constants;

import android.content.Context;

public class Constants
{
    /**
     * Defines several constants used between
     * {@link com.johnmelodyme.whiteflag.activities.DecisionActivity}
     * {@link com.johnmelodyme.whiteflag.activities.HelpSeekerActivity}
     * {@link com.johnmelodyme.whiteflag.activities.HelperActivity}
     */

    /* Debugging */
    public static String TAG_NAME = "white_flag";

    /* Log Level */
    public static String DEBUG_LEVEL = "[DEBUG] -> ";
    public static String INFO_LEVEL = "[INFO] -> ";
    public static String ERROR_LEVEL = "[ERROR] -> ";
    public static String WARN_LEVEL = "[WARNING] -> ";
    public static String VERBOSE_LEVEL = "[VERBOSE] -> ";

    /* Urls */
    public static String dev_profile_url = "https://play.google.com/store/apps/developer?id=John"
                                           + "+Melody+Me";
    public static String white_flag_movement = "https://bit.ly/36dRPsv";

    /* REST API */
    //    public static String url = "http://192.168.156.83/";
    public static String url = "https://benderaputihmalaysia.000webhostapp.com/";
    public static String get_data = url + "flag/api/view/";
    public static String insert_data = url + "flag/api/data/";
    public static String get_search_data = "flag/api/search/";

    public static String bpn_url = "https://api.whatsapp.com/send/?phone=60192615999&text"
                                   + "&app_absent=0";

    /**
     * Bank Name
     * Disallow User TO enter Banking info
     * {@link com.johnmelodyme.whiteflag.activities.HelpSeekerActivity}
     */
    public static String[] banks = new String[]{"Maybank", "CIMB", "Public bank"};

    public static String SERVER_KEY = "AAAAeo84ul0"
                                      +
                                      ":APA91bELDYHAbjf1TYICA2tzqEtFt2LKBlAWlJnei37u9CFjYImybusrbypNolF7iHTcEf6H2QLkoWINKdUFaSWZ_u_LrdqqM-N20Gv9SX1X6JSVfOipm3VYRLXfbgTxR2reApVuAqjm";
    public static String BASE_URL = "https://fcm.googleapis.com";
    public static String CONTENT_TYPE = "application/json";

    public static String API_KEY = "NGU5M2Y5MmQtMGQ2ZS00MTkxLWE1ODktMjE1NDE4ZWU0OTI4";

    public static String URL_BPN = "https://www.portalmalaysia.com/bakul-prihatin-negara/";

    public static String forum = "https://benderaputihmalaysia.createaforum.com/general-discussion/";

}
