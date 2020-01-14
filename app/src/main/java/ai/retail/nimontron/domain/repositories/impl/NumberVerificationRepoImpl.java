package ai.retail.nimontron.domain.repositories.impl;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import ai.retail.nimontron.data.DataManager;
import ai.retail.nimontron.domain.repositories.NumberVerificationRepo;
import ai.retail.nimontron.network.networkutils.Resource;
import ai.retail.nimontron.network.networkutils.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class NumberVerificationRepoImpl implements NumberVerificationRepo {

    private static final String TAG = "NumberVerificationRepoI";
    private static final String CREDENTIAL_NOT_FOUND = "Credential not found";
    protected DataManager mDataManager;
    private final int TIMEOUT_DURATION = 60;
    private final String VERIFICATION_FAILED = "Verification failed";
    private String mCode;
    private PhoneAuthProvider.ForceResendingToken mResendingToken;


    private FirebaseAuth mAuth;
    private Activity mActivity;
    private MutableLiveData<Resource<PhoneAuthCredential>> mLivePhoneAuth = new MutableLiveData<>();
    private MutableLiveData<Resource<FirebaseUser>> mVerifyCode = new MutableLiveData<>();

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String code, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            mCode = code;
            mResendingToken = forceResendingToken;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            mLivePhoneAuth.postValue(Resource.success(phoneAuthCredential));
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            e.printStackTrace();
            mLivePhoneAuth.postValue(Resource.<PhoneAuthCredential>error(VERIFICATION_FAILED));
        }
    };

    public NumberVerificationRepoImpl(DataManager dataManager, FirebaseAuth auth, Activity activity) {
        mDataManager = dataManager;
        mActivity = activity;
        mAuth = auth;
    }

    @Override
    public void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                TIMEOUT_DURATION,                // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                mActivity,               // Activity (for callback binding)
                mCallbacks);
    }

    @Override
    public void resendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                TIMEOUT_DURATION,                // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                mActivity,               // Activity (for callback binding)
                mCallbacks,
                mResendingToken);
    }

    @Override
    public void verifyCode(String code) throws IllegalArgumentException {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mCode, code);
        mLivePhoneAuth.setValue(Resource.success(credential));
    }

    @Override
    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        if (credential == null)
            mVerifyCode.postValue(Resource.<FirebaseUser>error(CREDENTIAL_NOT_FOUND));
        mVerifyCode.postValue(new Resource<FirebaseUser>(Status.LOADING, null, null));
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            mVerifyCode.setValue(Resource.success(user));
                        } else {
                            mVerifyCode.setValue(Resource.<FirebaseUser>error(task.getException().getLocalizedMessage()));
                        }
                    }
                });
    }

    @Override
    public MutableLiveData<Resource<FirebaseUser>> getLiveCodeVerification() {
        return mVerifyCode;
    }

    @Override
    public MutableLiveData<Resource<PhoneAuthCredential>> getLiveAuthCred() {
        return mLivePhoneAuth;
    }
}
