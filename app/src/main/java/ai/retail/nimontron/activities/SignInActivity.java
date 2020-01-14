package ai.retail.nimontron.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import ai.retail.nimontron.R;
import ai.retail.nimontron.databinding.ActivitySigninBinding;
import ai.retail.nimontron.helper.Navigator;
import ai.retail.nimontron.utils.CommonTasks;
import ai.retail.nimontron.viewmodels.AuthViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends BaseActivity<ActivitySigninBinding, AuthViewModel> implements View.OnClickListener {
    private static final String TAG = "SignInActivity";
    FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        bindVariables();
        initListeners();
    }

    private void initListeners() {
        mBinding.btnSignIn.setOnClickListener(this);
    }

    private void bindVariables() {
        mBinding.setSigninModel(mViewModel.getSignInModel());
    }

    private void initVariables() {
        mViewModel = provideViewModel(this, AuthViewModel.class);
        Log.d(TAG, "initVariables: " + mViewModel.toString());
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mFirebaseUser != null){
            Navigator.switchToHome(this);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_signin;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.btn_sign_in:
                if (CommonTasks.isOnline(this)){
                    String phoneNumber = getString(R.string.number_prefix) + mViewModel.getSignInModel().getPhoneNumber();
                    Navigator.switchToVerification(this, phoneNumber);
                }else {
                    showSnackBar(view, getString(R.string.please_keep_your_internet_connection_truned_on));
                }
                break;
        }
    }
}
