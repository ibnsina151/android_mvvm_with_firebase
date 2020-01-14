package ai.retail.nimontron.utils;

public interface Constants {
    String EMPTY_STRING = "";
    float LOCATION_ACCURACY_THRESHOLD = 20f;
    String IMAGE_FILE_SUFFIX = ".jpg";
    long NANO_SECOND = 1000000000;
    long LOCATION_UPDATE_TIME_THRESHOLD = 30; //second
    String DOUBLE_DIGIT_FORMAT = "00";
    long DEFAULT_TIME = 0;
    float OUTLET_LOCATION_VALIDATION_THRESHOLD = 30.0f;
    String DEFAULT_BMCC_CODE = "4000";

    double DEFAULT_DOUBLE = 0.0;
    String ANSWER_NO = "no";
    String ANSWER_YES = "yes";
    String SERVER_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    int DEFAULT_INT = 0;
    String URL_PREFIX = "http";
    CharSequence STRICT = "strict";
    String SPACE = " ";
    long ONE_DAY_MILL_SECOND = 24 * 60 * 60 * 1000;
    public static final String TAG_START_DATE_PICKER = "start_date_picker";
    public static final String TAG_RETURN_DATE_PICKER = "return_date_picker";
    String NOT_AVAILABLE = "N/A";
    long FIVE_MINUTE = 5 * 60 * 1000;
    int START_INDEX = 0;
    String COMMON_ERROR_MESSAGE = "Error occured. try again";

    // initialize qr variable as contant
    int MAX_MONTH_IN_YEAR = 11;
    String INPUT_TEXT_VALUE = "";
    boolean QR_YES = true;
    boolean QR_NO = false;
}
