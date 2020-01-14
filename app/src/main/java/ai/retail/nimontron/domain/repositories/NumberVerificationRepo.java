package ai.retail.nimontron.domain.repositories;

import androidx.lifecycle.MutableLiveData;

import ai.retail.nimontron.network.networkutils.Resource;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

public interface NumberVerificationRepo {

    void sendVerificationCode(String number);

    void resendVerificationCode(String number);

    void verifyCode(String code);

    void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential);

    MutableLiveData<Resource<FirebaseUser>> getLiveCodeVerification();

    MutableLiveData<Resource<PhoneAuthCredential>> getLiveAuthCred();
}
