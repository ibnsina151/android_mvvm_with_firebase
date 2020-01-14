package ai.retail.nimontron.firebase.references;

import androidx.lifecycle.LiveData;

import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;
import ai.retail.nimontron.network.networkutils.Resource;

public interface FirebaseReferences {

    LiveData<Resource> uploadBillBoardInfo(ImageMappingEntity imageMappingEntity, String userPhoneNumber);

}
