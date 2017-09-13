package com.testcase.demo.utils;

import android.text.TextUtils;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Class to validate strings for null or empty or email validation
 */
public class ValidateData {

    public static final Pattern USER_NAME
            = Pattern.compile(
            "^[A-Za-z0-9]+[.][A-Za-z0-9]+"
    );

    /**
     * Method to check if the passed string in empty or not
     *
     * @param valueToCheck the string value
     *
     * @return true if the string is not null or blank, false otherwise
     */
    public static boolean isNotNullOrBlank(String valueToCheck) {
        return null != valueToCheck && !TextUtils.isEmpty(valueToCheck.trim());
    }


    /**
     * Method to check if {@link Collection} is null or empty
     *
     * @param collection the Collection instance to be checked for non-empty
     *
     * @return true if collection has at-least one item
     */
    public static boolean isValidCollection(Collection collection) {
        return null != collection && !collection.isEmpty();
    }

    /**
     * Method to check the supplied string is a valid email
     *
     * @param val string to be checked for email-validation
     *
     * @return true if valid email, false otherwise
     */
    public static boolean isValidUserName(String val) {
        return isNotNullOrBlank(val) && USER_NAME.matcher(val).matches();
    }


}
