package ai.retail.nimontron.firebase.storage.impl;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ai.retail.nimontron.firebase.storage.FirebaseStorageHelper;
import ai.retail.nimontron.network.networkutils.Resource;
import ai.retail.nimontron.utils.FileUtils;
import ai.retail.nimontron.utils.ImageUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.File;

//import ai.retail.biponon.firebase.storage.FirebaseStorageHelper;
//import ai.retail.biponon.network.networkutils.Resource;
//import ai.retail.biponon.utils.Constants;
//import ai.retail.biponon.utils.FileUtils;
//import ai.retail.biponon.utils.ImageUtils;
//import ai.retail.biponon.utils.ListUtils;

public class FirebaseStorageHelperImpl implements FirebaseStorageHelper {

    public static final String IMAGE_NOT_FOUND = "Image not found";
    private static final String IMAGE_UPLOAD_FAILED = "Image upload failed";
    private StorageReference mStorageReference;


    private MutableLiveData<Resource<Uri>> mLiveBillboardImage;

    public FirebaseStorageHelperImpl(StorageReference storageReference) {
        mStorageReference = storageReference;
    }

    @Override
    public LiveData<Resource<Uri>> uploadBillBoardImage(final File file) {
        mLiveBillboardImage = new MutableLiveData<>();

        final StorageReference imageStoreRef = mStorageReference.child(CHILD_BILLBOARD_IMAGE + file.getName());
        mLiveBillboardImage.setValue(Resource.<Uri>loading(null));
        if (FileUtils.isUrl(file.getPath())) {
            mLiveBillboardImage.setValue(Resource.success(Uri.fromFile(file)));
            return mLiveBillboardImage;
        }

        try{
            ByteArrayInputStream compressedByteArray = new ByteArrayInputStream(ImageUtils.compressImage(file.getPath()));
            final UploadTask uploadTask = imageStoreRef.putStream(compressedByteArray);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageStoreRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            mLiveBillboardImage.setValue(Resource.success(uri));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mLiveBillboardImage.setValue(Resource.<Uri>error(IMAGE_UPLOAD_FAILED));
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mLiveBillboardImage.setValue(Resource.<Uri>error(IMAGE_UPLOAD_FAILED));
                }
            });
        }catch (Exception ex){
            mLiveBillboardImage.setValue(Resource.<Uri>error(IMAGE_NOT_FOUND));
        }
        return mLiveBillboardImage;
    }

}
