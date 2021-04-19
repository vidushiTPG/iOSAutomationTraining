package com.tpg.appium.learning.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtility {

    public static String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
