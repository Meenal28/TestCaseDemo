package com.testcase.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to save utility methods commonly used in app
 */
public class Utility {


    /**
     * method to format date/time received from server  to desired date/time format
     *
     * @param initiativeDatetime date/time received from server
     *
     * @return formatted date
     */
    public static String formateDate(String initiativeDatetime) {


        if (initiativeDatetime == null || initiativeDatetime.equalsIgnoreCase("")) {
            return "NA";
        }

        String formattedDate = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.REQFORMAT);
        Date myDate = null;
        try {
            myDate = dateFormat.parse(initiativeDatetime);
            SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.NEW_DESIRED_FORMAT);
            formattedDate = timeFormat.format(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    /**
     * method to get current date time in particular format
     *
     * @return returns the formatted date time
     */
    public static String getCurrentDateTimeFormatted() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);

        String formattedDate = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.REQFORMAT);
            formattedDate = dateFormat.format(calendar.getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedDate;
    }


    /**
     * Method to verfiy if received data is null or not
     *
     * @param stringText string received
     *
     * @return if null return default value else return original value
     */
    public static String getStringText(String stringText) {
        if (ValidateData.isNotNullOrBlank(stringText)) {
            return stringText;
        } else {
            return "NA";
        }
    }


    /**
     * Pass your date format and no of days for minus/add from current
     * If you want to get previous date then pass days with minus sign
     * else you can pass as it is for next date
     *
     * @param dateFormat
     * @param days
     *
     * @return Calculated Date
     */
    public static String getCalculatedDate(String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

    /**
     * method to compare date/time received from server  with calculated date
     *
     * @param sprintEndDatetime date/time received from server
     *
     * @return No of days
     */
    public static int compareDate(String sprintEndDatetime) {


        try {

            if (sprintEndDatetime == null || sprintEndDatetime.equalsIgnoreCase("")) {
                return Constants.INT_0;
            }
            String endDateFormatted = formateDate(sprintEndDatetime);
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.NEW_DESIRED_FORMAT);

            Date endDate = formatter.parse(endDateFormatted);

            String str1DayDate = getCalculatedDate(Constants.NEW_DESIRED_FORMAT, Constants.INT_1);
            Date date1Day = formatter.parse(str1DayDate);

            String str5DayDate = getCalculatedDate(Constants.NEW_DESIRED_FORMAT, Constants.INT_5);
            Date date5Day = formatter.parse(str5DayDate);


            return date1Day.compareTo(endDate) == 0 ? Constants.INT_1 : date5Day.compareTo(endDate) == 0 ? Constants.INT_5 : 0;


        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        return 0;
    }

}
