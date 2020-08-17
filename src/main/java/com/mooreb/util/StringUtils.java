package com.mooreb.util;

public class StringUtils {
    public static boolean isEmpty(final String s) {
        return ((null == s) || s.trim().isEmpty());
    }
    public static String myPercent(final double d) {
        final String retval = String.format("%6.2f%%", 100.0*d);
        return retval;
    }
}
