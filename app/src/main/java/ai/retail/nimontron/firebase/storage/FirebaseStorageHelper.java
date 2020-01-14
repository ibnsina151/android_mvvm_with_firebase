package ai.retail.nimontron.firebase.storage;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import ai.retail.nimontron.network.networkutils.Resource;

import java.io.File;

//import ai.retail.biponon.network.networkutils.Resource;

public interface FirebaseStorageHelper {
    String FIREBASE_STORAGE_URL = "gs://billboard-9db62.appspot.com";
    String CHILD_BILLBOARD_IMAGE = "billboardImage/";


    LiveData<Resource<Uri>> uploadBillBoardImage(File file);

}
