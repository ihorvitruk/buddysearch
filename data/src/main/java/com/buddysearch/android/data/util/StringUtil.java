package com.buddysearch.android.data.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    /**
     * Examples:
     * concatLinearly("_", one, 123) -> one_123
     * concatLinearly("-", null, 123) -> 123
     * concatLinearly("-", one, null) -> one
     */
    public static String concatLinearly(String separator, String... strings) {
        String fullString = "";
        if (separator == null || strings == null) {
            return null;
        }

        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            if (s != null && s.length() > 0) {
                fullString += s;
                if (i < strings.length - 1) {
                    fullString += separator;
                }
            }
        }
        return fullString;
    }
}
