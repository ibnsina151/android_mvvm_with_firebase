package ai.retail.nimontron.firebase.references.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;
import ai.retail.nimontron.firebase.datamodels.UpdateBillboardModel;
import ai.retail.nimontron.firebase.references.FirebaseReferences;
import ai.retail.nimontron.network.networkutils.Resource;
import ai.retail.nimontron.utils.Conversion;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import static ai.retail.nimontron.utils.CommonTasks.getCurrentDate;
import static ai.retail.nimontron.utils.CommonTasks.getCurrentMonth;
import static ai.retail.nimontron.utils.CommonTasks.getCurrentYear;

public class FirebaseReferencesImpl implements FirebaseReferences {
    private static final String TAG = "FirebaseReferencesImpl";

    private static final String NODE_BILL_BOARD_ROOT = "userinfo";

    private DatabaseReference mDatabaseReference;
    private MutableLiveData<Resource> mLiveUploadBillBoardInfo = new MutableLiveData<>();

    public FirebaseReferencesImpl(DatabaseReference databaseReference) {
        mDatabaseReference = databaseReference;
    }

    @Override
    public LiveData<Resource> uploadBillBoardInfo(ImageMappingEntity imageMappingEntity, String userPhoneNumber) {
        mLiveUploadBillBoardInfo.setValue(Resource.loading(null));
        String year = getCurrentYear();
        String month = getCurrentMonth();
        String date = getCurrentDate();

        DatabaseReference dbRef = mDatabaseReference.child(NODE_BILL_BOARD_ROOT)
                .child(imageMappingEntity.getPhoneNumber())
                .child(year)
                .child(month)
                .child(date);

        UpdateBillboardModel updateBillboardModel =  Conversion.getUpdateOfBillboard(userPhoneNumber,imageMappingEntity);
        dbRef.push().setValue(updateBillboardModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError != null){
                    mLiveUploadBillBoardInfo.setValue(Resource.error(databaseError.getMessage()));
                }else{
                    mLiveUploadBillBoardInfo.setValue(Resource.success((null)));
                }
            }
        });

        return mLiveUploadBillBoardInfo;
    }
}
