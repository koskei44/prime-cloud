package com.pm.primeerp.util;

import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Util {

    public static String getCurrentTimeSimple() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getLocalDataString(String format) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss 'GMT' yyyy", Locale.getDefault());
        inputFormat.setTimeZone(TimeZone.getDefault());

        SimpleDateFormat outputFormat = new SimpleDateFormat(format);

        return outputFormat.format(new Date());
    }

    public static String changeDateFormatToUserReadable(String dateformatString) {

        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-M-d");
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");
        Date date = null;
        String formattedDate = null;
        try {
            date = originalFormat.parse(dateformatString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        formattedDate = targetFormat.format(date);
        return formattedDate;
    }

    public static String changeDateFormatTOSimple(String dateformatString) {

        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-M-d");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String formattedDate = null;
        try {
            date = originalFormat.parse(dateformatString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        formattedDate = targetFormat.format(date);
        return formattedDate;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float) maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float) maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        return mSDF.format(new Date());
    }

}
