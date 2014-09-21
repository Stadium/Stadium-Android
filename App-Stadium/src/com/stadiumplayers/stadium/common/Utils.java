
package com.stadiumplayers.stadium.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.stadiumplayers.stadium.App;
import com.stadiumplayers.stadium.R;

public class Utils {

    private static final String SDF_DAY_OF_WEEK = "EEEE";

    /**
     * @param quantityStringId The desired resource identifier
     * @param quantity The number used to get the correct string for the current
     *            language's plural rules.
     * @return The string data associated with the resource, stripped of styled
     *         text information.
     */
    public static String getQuantityString(int quantityStringId, int quantity) {
        return App.getContext().getResources().getQuantityString(quantityStringId, quantity);
    }

    /**
     * @param stringArrayId The desired resource identifier
     * @return The string array associated with the resource.
     */
    public static String[] getStringArray(int stringArrayId) {
        return App.getContext().getResources().getStringArray(stringArrayId);
    }

    /**
     * @param stringId The desired resource identifier
     * @return The string data associated with the resource, stripped of styled
     *         text information.
     */
    public static String getString(int stringId) {
        return App.getContext().getResources().getString(stringId);
    }

    /**
     * @param colorId The desired resource identifier
     * @return Returns a single color value in the form 0xAARRGGBB.
     */
    public static int getColor(int colorId) {
        return App.getContext().getResources().getColor(colorId);
    }

    /**
     * @param typedArrayId The desired resource identifier
     * @return Returns a TypedArray holding an array of the array values. Be
     *         sure to call TypedArray.recycle() when done with it.
     */
    @SuppressLint("Recycle")
    public static TypedArray obtainTypedArray(int typedArrayId) {
        return App.getContext().getResources().obtainTypedArray(typedArrayId);
    }

    /**
     * @param drawableId The desired resource identifier
     * @return An object that can be used to draw this resource
     */
    public static Drawable getDrawable(int drawableId) {
        return App.getContext().getResources().getDrawable(drawableId);
    }

    /**
     * Helper method to grab the date format from the system
     * 
     * @param context A context for the App to grab the System's date formatting
     * @return String to be used for SimpleDateFormat
     */
    public static String detectDateFormat(boolean includeYear) {
        Calendar testDate = Calendar.getInstance();
        testDate.set(Calendar.YEAR, 2013);
        testDate.set(Calendar.MONTH, Calendar.DECEMBER);
        testDate.set(Calendar.DAY_OF_MONTH, 25);

        Format format = android.text.format.DateFormat.getDateFormat(App.getContext());
        String testDateFormat = format.format(testDate.getTime());
        String[] parts = testDateFormat.split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : parts) {
            if (s.equals("25")) {
                stringBuilder.append("dd ");
            }
            if (s.equals("12")) {
                stringBuilder.append("MMM ");
            }
            if (s.equals("2013") && includeYear) {
                stringBuilder.append("yyyy ");
            }
        }
        return stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
    }

    /**
     * Helper method to grab the User's chosen time formatting (from the system)
     * 
     * @param context A context for the for the App to grab the System's time
     *            formatting
     * @return String String representing the time format as a SimpleDateFormat
     */
    public static String detectTimeFormat() {
        return DateFormat.is24HourFormat(App.getContext()) ? "HH:mm" : "h:mm a";
    }

    /**
     * @return True if the time format is 24 hour, (Set in the Android's System
     *         Clock settings)
     */
    public static boolean is24Hour() {
        return DateFormat.is24HourFormat(App.getContext());
    }

    /**
     * Formats the given time into the appropriate format as a string
     * 
     * @param epochTime The time in the future to compare against today
     * @return The appropriate formatted date as a string
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFriendlyDateFor(long epochTime) {
        boolean verboseTimeStampNeeded = true;
        Time currentTime = new Time();
        Time eventTime = new Time();
        StringBuilder stringBuilder = new StringBuilder();

        currentTime.set(System.currentTimeMillis());
        eventTime.set(epochTime);

        // In the past. (Invalid)
        if (epochTime < System.currentTimeMillis()) {
            return Utils.getString(R.string.time_old);
        }

        // Same year
        if (currentTime.year == eventTime.year) {

            // Same hour
            if (currentTime.yearDay == eventTime.yearDay && eventTime.hour <= currentTime.hour + 1) {

                verboseTimeStampNeeded = false;
                stringBuilder
                        .append(eventTime.minute - currentTime.minute)
                        .append(" ")
                        .append(Utils.getQuantityString(R.plurals.time_unit_minute,
                                eventTime.minute - currentTime.minute));

                // Same day
            } else if (currentTime.yearDay == eventTime.yearDay) {
                stringBuilder.append(Utils.getString(R.string.today));

                // Tomorrow
            } else if (currentTime.yearDay == eventTime.yearDay - 1) {
                stringBuilder.append(Utils.getString(R.string.tomorrow));

                // Within a week
            } else if (eventTime.yearDay <= currentTime.yearDay + 6) {
                stringBuilder.append(new SimpleDateFormat(SDF_DAY_OF_WEEK).format(new Date(
                        eventTime.toMillis(false))));

                // Same year
            } else {
                stringBuilder.append(App.getDateFormat()
                        .format(new Date(eventTime.toMillis(false))));
            }

            // Different year
        } else {
            stringBuilder.append(App.getDateFormatVerbose().format(
                    new Date(eventTime.toMillis(false))));
        }

        if (verboseTimeStampNeeded) {
            stringBuilder.append(" ").append(Utils.getString(R.string.at)).append(" ")
                    .append(App.getTimeFormat().format(new Date(eventTime.toMillis(false))));
        }

        return stringBuilder.toString();
    }

    /**
     * @return A String representation of the currency symbol based on the
     *         user's Locale
     */
    public static String getCurrencySymbol() {
        return Currency.getInstance(Locale.getDefault()).getSymbol();
    }

    public static void logKeyHash(Context context) {
        PackageInfo info;
        try {
            info = context.getPackageManager().getPackageInfo("com.stadiumplayers.stadium",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                // String something = new
                // String(Base64.encodeBytes(md.digest()));
                Log.e("hash-key", something);
            }
        } catch (NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }
}
