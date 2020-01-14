package ai.retail.nimontron.helper;

import com.crashlytics.android.Crashlytics;

public class CrashlyticsHelper {
    public void setUserIdentifier(String id){
        Crashlytics.setUserIdentifier(id);
    }

    public void setUserEmail(String email){
        Crashlytics.setUserEmail(email);
    }

    public void setUserName(String name){
        Crashlytics.setUserName(name);
    }
}
