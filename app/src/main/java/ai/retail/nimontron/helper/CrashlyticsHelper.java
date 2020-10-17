package ai.retail.nimontron.helper;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class CrashlyticsHelper {

    private FirebaseCrashlytics mFirebaseCrashlytics;

    public CrashlyticsHelper(FirebaseCrashlytics mFirebaseCrashlytics) {
        mFirebaseCrashlytics.setCrashlyticsCollectionEnabled(true);
        this.mFirebaseCrashlytics = mFirebaseCrashlytics;

    }
    public void setUserIdentifier(String id) {
        mFirebaseCrashlytics.setUserId(id);
    }

    public void setUserEmail(String email) {
    }

    public void setUserName(String name) {
//        mFirebaseCrashlytics.setuserNmae(name);
    }

    public void setLogExceptoin(Exception e){
        mFirebaseCrashlytics.recordException(e);
    }

    public  <T> void setException(T exception){
        mFirebaseCrashlytics.recordException((Throwable) exception);
    }

    public void setLog(String log){
        mFirebaseCrashlytics.log(log);
    }


    public void  setCustomCrash(String key, String val){
        mFirebaseCrashlytics.setCustomKey(key,val);
    }
}
