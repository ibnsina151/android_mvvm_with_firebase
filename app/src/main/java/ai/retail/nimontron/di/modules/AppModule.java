package ai.retail.nimontron.di.modules;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import ai.retail.nimontron.data.DataManager;
import ai.retail.nimontron.data.DbHelper;
import ai.retail.nimontron.data.PreferenceHelper;
import ai.retail.nimontron.data.impl.DataManagerImpl;
import ai.retail.nimontron.data.impl.DbHelperImpl;
import ai.retail.nimontron.data.impl.PreferenceHelperImpl;
import ai.retail.nimontron.dbhelper.DbConstants;
import ai.retail.nimontron.dbhelper.database.ProjectDB;
import ai.retail.nimontron.di.ApplicationContext;
import ai.retail.nimontron.di.ApplicationScope;
import ai.retail.nimontron.di.DatabaseInfo;
import ai.retail.nimontron.di.FirebaseStorageInfo;
import ai.retail.nimontron.di.PreferenceInfo;
import ai.retail.nimontron.firebase.references.FirebaseReferences;
import ai.retail.nimontron.firebase.references.impl.FirebaseReferencesImpl;
import ai.retail.nimontron.firebase.storage.FirebaseStorageHelper;
import ai.retail.nimontron.firebase.storage.impl.FirebaseStorageHelperImpl;
import ai.retail.nimontron.helper.CrashlyticsHelper;
import ai.retail.nimontron.helper.LocationManager;
import ai.retail.nimontron.helper.PermissionManager;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @ApplicationScope
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return DbConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String provideSharedPrefName(){return PreferenceHelper.MODULE_NAME;}

    @Provides
    @FirebaseStorageInfo
    String provideFirebaseStorage() {
        return FirebaseStorageHelper.FIREBASE_STORAGE_URL;
    }

    @Provides
    @PreferenceInfo
    int provideSharedPrefVersion(){return PreferenceHelper.VERSION;}

    @Provides
    @ApplicationScope
    DataManager provideDataManager(DbHelper dbHelper, PreferenceHelper preferenceHelper, FirebaseReferences firebaseReferences) {
        return new DataManagerImpl(dbHelper, preferenceHelper, firebaseReferences);
    }

    @Provides
    @ApplicationScope
    DbHelper provideDbHelper(ProjectDB projectDB) {
        return new DbHelperImpl(projectDB);
    }


    @Provides
    @ApplicationScope
    ProjectDB provideAppDatabase(@DatabaseInfo String dbName, @ApplicationContext Context context) {
        return Room.databaseBuilder(context, ProjectDB.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @ApplicationScope
    PreferenceHelper provideSharedPref(@ApplicationContext Context context,
                                       @PreferenceInfo String moduleName,
                                       @PreferenceInfo int version){
        return new PreferenceHelperImpl(context, moduleName, version);
    }

    @Provides
    @ApplicationScope
    PermissionManager getPermissionManager(){
        return new PermissionManager();
    }

    @Provides
    @ApplicationScope
    LocationManager getLocationManager(@ApplicationContext Context context) {
        return new LocationManager(context);
    }

    @Provides
    @ApplicationScope
    DatabaseReference getDataReference() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        return firebaseDatabase.getReference();
    }

    @Provides
    @ApplicationScope
    FirebaseReferences getFirebaseReference(DatabaseReference databaseReference) {
        return new FirebaseReferencesImpl(databaseReference);
    }

    @Provides
    @ApplicationScope
    FirebaseCrashlytics getFirebaseCrashlytics(){
        return FirebaseCrashlytics.getInstance();
    }


    @Provides
    @ApplicationScope
    CrashlyticsHelper getCrashlyticsHelper(FirebaseCrashlytics firebaseCrashlytics) {
        return new CrashlyticsHelper(firebaseCrashlytics);
    }


    @Provides
    @ApplicationScope
    StorageReference getFirebaseStorageReference(@FirebaseStorageInfo String url) {
        return FirebaseStorage.getInstance().getReferenceFromUrl(url);
    }

    @Provides
    @ApplicationScope
    FirebaseStorageHelper getFirebaseStorageHelper(StorageReference storageReference) {
        return new FirebaseStorageHelperImpl(storageReference);
    }

}
