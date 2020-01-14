package ai.retail.nimontron.data.impl;

import androidx.lifecycle.LiveData;

import ai.retail.nimontron.data.DataManager;
import ai.retail.nimontron.data.DbHelper;
import ai.retail.nimontron.data.PreferenceHelper;
import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;
import ai.retail.nimontron.firebase.references.FirebaseReferences;
import ai.retail.nimontron.network.networkutils.Resource;

import java.util.List;

public class DataManagerImpl implements DataManager {

    private DbHelper mDbHelper;
    private PreferenceHelper mPreferenceHelper;
    private FirebaseReferences mFirebaseReferences;

    public DataManagerImpl(DbHelper dbHelper, PreferenceHelper preferenceHelper, FirebaseReferences firebaseReferences) {
        mDbHelper = dbHelper;
        mPreferenceHelper = preferenceHelper;
        mFirebaseReferences = firebaseReferences;
    }

    @Override
    public void saveBillBoardInfo(ImageMappingEntity imageMappingEntity) {
         mDbHelper.saveBillBoardInfo(imageMappingEntity);
    }

    @Override
    public LiveData<List<ImageMappingEntity>> getBillBoardInfo(String number) {
        return mDbHelper.getBillBoardInfo(number);
    }


    @Override
    public void deleteAllBillBoardInfo() {
        mDbHelper.deleteAllBillBoardInfo();
    }

    @Override
    public LiveData<Resource> uploadBillBoardInfo(ImageMappingEntity imageMappingEntity, String userPhoneNumber) {
        return mFirebaseReferences.uploadBillBoardInfo(imageMappingEntity,userPhoneNumber);
    }
}
