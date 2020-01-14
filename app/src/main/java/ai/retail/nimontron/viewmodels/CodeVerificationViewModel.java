package ai.retail.nimontron.viewmodels;

import androidx.lifecycle.MutableLiveData;

import ai.retail.nimontron.domain.repositories.NumberVerificationRepo;
import ai.retail.nimontron.network.networkutils.Resource;
import ai.retail.nimontron.viewmodels.datamodels.CodeVerificationModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

import javax.inject.Inject;

public class CodeVerificationViewModel extends BaseViewModel {

    private NumberVerificationRepo mNumberVerificationRepo;
    private CodeVerificationModel mCodeVerificationModel;

    @Inject
    public CodeVerificationViewModel(NumberVerificationRepo numberVerificationRepo) {
        mNumberVerificationRepo = numberVerificationRepo;
        mCodeVerificationModel = new CodeVerificationModel();
    }

    public CodeVerificationModel getCodeVerificationModel() {
        return mCodeVerificationModel;
    }

    public void verifyCode() throws IllegalArgumentException {
        mNumberVerificationRepo.verifyCode(mCodeVerificationModel.getCode());
    }

    public void sendVerificationCode() {
        mNumberVerificationRepo.sendVerificationCode(mCodeVerificationModel.getPhoneNumber());
    }

    public void resendVerificationCode() {
        mNumberVerificationRepo.resendVerificationCode(mCodeVerificationModel.getPhoneNumber());
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mNumberVerificationRepo.signInWithPhoneAuthCredential(credential);
    }

    public MutableLiveData<Resource<PhoneAuthCredential>> getAuthCredObserver() {
        return mNumberVerificationRepo.getLiveAuthCred();
    }

    public MutableLiveData<Resource<FirebaseUser>> getCodeVerificationObserver() {
        return mNumberVerificationRepo.getLiveCodeVerification();
    }
}
