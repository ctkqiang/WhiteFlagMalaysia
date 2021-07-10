package com.johnmelodyme.whiteflag.constants;

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
    public static String dev_profile_url = "https://play.google.com/store/apps/developer?id=John+Melody+Me";
    public static String white_flag_movement = "https://bit.ly/36dRPsv";

    /* REST API */
//    public static String url = "http://192.168.156.83/";
    public static String url = "http://benderaputih.000webhostapp.com/";
    public static String get_data = url + "flag/api/view/";
    public static String insert_data = url + "flag/api/data/";
    public static String get_search_data = "flag/api/search/";

    /**
     * Bank Name
     * Disallow User TO enter Banking info
     * {@link com.johnmelodyme.whiteflag.activities.HelpSeekerActivity}
     */
    public static String[] banks = new String[]{"Maybank", "CIMB", "Public bank"};

}
