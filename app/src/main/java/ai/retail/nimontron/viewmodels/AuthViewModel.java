package ai.retail.nimontron.viewmodels;

import ai.retail.nimontron.viewmodels.datamodels.SignInModel;

import javax.inject.Inject;


public class AuthViewModel extends BaseViewModel {

    private static final String TAG = "AuthViewModel";
    private SignInModel mSignInModel;

    @Inject
    public AuthViewModel() {
        mSignInModel = new SignInModel();
    }

    public SignInModel getSignInModel() {
        return mSignInModel;
    }
}
