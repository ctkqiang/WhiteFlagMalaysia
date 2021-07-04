package com.johnmelodyme.whiteflag.constants;

public enum LogLevel
{
    DEBUG
            {
                public String to_string()
                {
                    return Constants.DEBUG_LEVEL;
                }
            },
    ERROR
            {
                public String to_string()
                {
                    return Constants.ERROR_LEVEL;
                }
            },
    WARN
            {
                public String to_string()
                {
                    return Constants.WARN_LEVEL;
                }
            },
    INFO
            {
                public String to_string()
                {
                    return Constants.INFO_LEVEL;
                }
            },
    VERBOSE
            {
                public String to_string()
                {
                    return Constants.VERBOSE_LEVEL;
                }
            }
}
