package ai.retail.nimontron.helper;

import android.content.Context;
import android.content.Intent;

import ai.retail.nimontron.activities.CodeVerificationActivity;
import ai.retail.nimontron.activities.MainActivity;
import ai.retail.nimontron.activities.SignInActivity;

public class Navigator {

    public static void switchToSignIn(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void switchToVerification(Context context, String phoneNumber) {
        Intent intent = new Intent(context, CodeVerificationActivity.class);
        intent.putExtra(CodeVerificationActivity.KEY_TAG_PHONE_NUMBER, phoneNumber);
        context.startActivity(intent);
    }

    public static void switchToHome(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

//    public static void switchToOutletList(Context context) {
//        context.startActivity(new Intent(context, OutletListActivity.class));
//    }
//
//    public static void switchToAgentOutlet(Context context, String number) {
//        Intent intent = new Intent(context, AgentOutletDetailsActivity.class);
//        intent.putExtra(AgentOutletDetailsActivity.KEY_PHONE_NUMBER, number);
//        context.startActivity(intent);
//    }
//
//    public static void switchToMerchantOutlet(Context context, String number) {
//        Intent intent = new Intent(context, MerchantOutletDetailsActivity.class);
//        intent.putExtra(OutletDetailsActivity.KEY_PHONE_NUMBER, number);
//        context.startActivity(intent);
//    }
//
//    public static void switchToSavedOutletList(Context context) {
//        context.startActivity(new Intent(context, SavedOutletListActivity.class));
//    }
//
//    public static void switchToHelpline(Context context){
//        context.startActivity(new Intent(context, HelplineActivity.class));
//    }
//
//    public static void switchToUploadHistory(Context context) {
//        Intent intent = new Intent(context, UploadHistoryActivity.class);
//        context.startActivity(intent);
//    }
//
//    public static void switchToUploadHistoryDetails(Context context, String date) {
//        Intent intent = new Intent(context, UploadHistoryDetailsActivity.class);
//        intent.putExtra(UploadHistoryDetailsActivity.KEY_DATE, date);
//        context.startActivity(intent);
//    }
//
//    public static void switchToSetTime(Activity activity) {
//        activity.startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
//        activity.finish();
//    }
//
//    public static void switchToCompetitionOutletList(Activity activity) {
//        Intent intent = new Intent(activity, CompetitionActivity.class);
//        activity.startActivity(intent);
//    }
//
//    public static void switchToCompetitorInfo(Activity activity, OutletModel outletModel) {
//        Intent intent = new Intent(activity, CompetitorInfoActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(CompetitorInfoActivity.KEY_OUTLET_DETAILS, outletModel);
//        intent.putExtras(bundle);
//        activity.startActivity(intent);
//    }
//
//    public static void switchToAddInventory(Activity activity) {
//        Intent intent = new Intent(activity, InventoryActivity.class);
//        activity.startActivity(intent);
//    }
//
//    public static void switchToApplyLeave(Activity activity) {
//        activity.startActivity(new Intent(activity, LeaveActivity.class));
//    }
//
//    public static void switchToNotification(Activity activity) {
//        activity.startActivity(new Intent(activity,NotificationActivity.class));
//    }
}
