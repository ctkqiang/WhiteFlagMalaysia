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
    public static String DEBUG_LEVEL = "*DEBUG* -> ";
    public static String INFO_LEVEL = "*INFO* -> ";
    public static String ERROR_LEVEL = "*ERROR* -> ";
    public static String WARN_LEVEL = "*WARNING* -> ";
    public static String VERBOSE_LEVEL = "*VERBOSE* -> ";

    /* Urls */
    public static String dev_profile_url = "https://johnmelodyme.github.io/";

    /* REST API */
    public static String url = "http://192.168.156.83/"; // TODO replace in production
    public static String get_data = url + "white_flags/api/view/";
    public static String insert_data = url + "white_flags/api/data/";
}
