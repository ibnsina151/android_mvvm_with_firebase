package ai.retail.nimontron.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import ai.retail.nimontron.R;
import ai.retail.nimontron.network.ApiConstant;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonTasks {
    public String val = "";

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getCurrentDay() {
        return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDate();
    }

    public static String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static String getCurrentMonth() {
        DecimalFormat format = new DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT);
        Calendar calendar = Calendar.getInstance();
        return format.format(Double.valueOf(calendar.get(Calendar.MONTH) + 1));
    }

    public static String getCurrentDate() {
        DecimalFormat format = new DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT);
        Calendar calendar = Calendar.getInstance();
        return format.format(Double.valueOf(calendar.get(Calendar.DATE)));
    }


//    public static int getDayResId(int dayOfWeek) {
//        switch (dayOfWeek) {
//            case 1:
//                return R.string.text_week_day_1;
//            case 2:
//                return R.string.text_week_day_2;
//            case 3:
//                return R.string.text_week_day_3;
//            case 4:
//                return R.string.text_week_day_4;
//            case 5:
//                return R.string.text_week_day_5;
//            case 6:
//                return R.string.text_week_day_6;
//            case 7:
//                return R.string.text_week_day_7;
//        }
//        return 0;
//    }
//
//    public static int getMonthResId(int monthOfYear) {
//        switch (monthOfYear) {
//            case 0:
//                return R.string.text_month_1;
//            case 1:
//                return R.string.text_month_2;
//            case 2:
//                return R.string.text_month_3;
//            case 3:
//                return R.string.text_month_4;
//            case 4:
//                return R.string.text_month_5;
//            case 5:
//                return R.string.text_month_6;
//            case 6:
//                return R.string.text_month_7;
//            case 7:
//                return R.string.text_month_8;
//            case 8:
//                return R.string.text_month_9;
//            case 9:
//                return R.string.text_month_10;
//            case 10:
//                return R.string.text_month_11;
//            case 11:
//                return R.string.text_month_12;
//        }
//        return 0;
//    }

//    public static String getUploadHistoryFormatDate(Context context, String uploadDate) {
//        String resDate = Constants.EMPTY_STRING;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        Date date = null;
//        try {
//            date = dateFormat.parse(uploadDate);
//            calendar.setTime(date);
//            String day = context.getString(CommonTasks.getDayResId(calendar.get(Calendar.DAY_OF_WEEK)));
//            String month = context.getString(CommonTasks.getMonthResId(calendar.get(Calendar.MONTH)));
//            int dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//            int year = calendar.get(Calendar.YEAR);
//            resDate = String.format(context.getString(R.string.upload_history_date_format), day, month, dateOfMonth, year);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            return resDate;
//        }
//    }

    public static boolean isOnline(Context context) {
        if (context == null) {
            return false;
        } else {
            try {
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static boolean isTaskMandatory(String taskType) {
        return TextUtils.equals(taskType, Constants.STRICT);
    }

    public static String genAccessToken(String accessToken) {
        return ApiConstant.BEARER + Constants.SPACE + accessToken;
    }


    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            View focusedView = activity.getCurrentFocus();
            if (focusedView == null) return;
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public static void hideSoftKeyboard(Activity activity, View view) {
        try {
            if (view != null && activity != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
        }
    } public static void hideSoftKeyboard_For_InputDialog(Context mContext, View v) {
        try {
            if (v != null && mContext != null) {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Provide app version dynamically from app package.
     *
     * @param context Context to get package
     * @return app version as string
     */
    public static String getAppVersion(Context context) {
        String version = Constants.EMPTY_STRING;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            version = Constants.NOT_AVAILABLE;
        }
        return version;
    }

}