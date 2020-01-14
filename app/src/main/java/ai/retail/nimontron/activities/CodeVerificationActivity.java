package ai.retail.nimontron.activities;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;

import androidx.lifecycle.Observer;

import ai.retail.nimontron.R;
import ai.retail.nimontron.databinding.ActivityCodeVerificationBinding;
import ai.retail.nimontron.helper.Navigator;
import ai.retail.nimontron.network.networkutils.Resource;
import ai.retail.nimontron.utils.CommonTasks;
import ai.retail.nimontron.utils.Constants;
import ai.retail.nimontron.viewmodels.CodeVerificationViewModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

public class CodeVerificationActivity extends BaseActivity<ActivityCodeVerificationBinding, CodeVerificationViewModel> implements View.OnClickListener {

    public static final String KEY_TAG_PHONE_NUMBER = "key_phone_number";
    private Observer<Resource<PhoneAuthCredential>> mAuthCredObserver = new Observer<Resource<PhoneAuthCredential>>() {
        @Override
        public void onChanged(Resource<PhoneAuthCredential> credential) {
            switch (credential.status) {
                case ERROR:
                    CommonTasks.showToast(CodeVerificationActivity.this, credential.message);
                    break;
                case SUCCESS:

                    mViewModel.signInWithPhoneAuthCredential(credential.data);
                    String smsCode = credential.data.getSmsCode();
                    mBinding.tieVerificationCode.setText(smsCode);
                    if (!TextUtils.isEmpty(smsCode))
                        mBinding.tieVerificationCode.setSelection(smsCode.length());
                    break;
            }
        }
    };

    private Observer<Resource<FirebaseUser>> mCodeVerificationObserver = new Observer<Resource<FirebaseUser>>() {
        @Override
        public void onChanged(Resource<FirebaseUser> firebaseUserResource) {
            switch (firebaseUserResource.status) {
                case ERROR:
                    cancelProgressDialog();
                    CommonTasks.showToast(CodeVerificationActivity.this, firebaseUserResource.message);
                    break;
                case SUCCESS:
                    cancelProgressDialog();
                    Navigator.switchToHome(CodeVerificationActivity.this);
                    break;
                case LOADING:
                    showProgressDialog();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_code_verification;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        extractData();
        bindVariables();
        setCodeResendText();
        initListeners();
        bindObservers();
        sendVerificationCode();
    }

    private void setCodeResendText() {
        final SpannableString sb = new SpannableString(getResources().getString(R.string.text_resend_code));
        final ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        sb.setSpan(fcs, 12, 27, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(bss, 12, 27, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mBinding.tvResendCode.setText(sb);
    }


    private void bindObservers() {
        mViewModel.getAuthCredObserver().observe(this, mAuthCredObserver);
        mViewModel.getCodeVerificationObserver().observe(this, mCodeVerificationObserver);
    }

    private void initListeners() {
        mBinding.btnVerifyCode.setOnClickListener(this);
        mBinding.tvResendCode.setOnClickListener(this);
    }

    private void bindVariables() {
        mBinding.setCodeModel(mViewModel.getCodeVerificationModel());
        String message = String.format(getString(R.string.text_verify_phone_number_subtitle), mViewModel.getCodeVerificationModel().getPhoneNumber());
        mBinding.tvVerifyNumberSubtitle.setText(message);
    }

    private void extractData() {
        mViewModel.getCodeVerificationModel().setPhoneNumber(getIntent().getExtras().getString(KEY_TAG_PHONE_NUMBER, Constants.EMPTY_STRING));
    }

    private void initVariables() {
        mViewModel = provideViewModel(this, CodeVerificationViewModel.class);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case R.id.btn_verify_code:
                verifyCode();
                break;
            case R.id.tv_resend_code:
                CommonTasks.showToast(this, getString(R.string.toast_code_resent));
                resendCode();
                break;
        }
    }

    private void resendCode() {
        mViewModel.resendVerificationCode();
        setCodeResendText();

    }

    private void sendVerificationCode() {
        mViewModel.sendVerificationCode();
    }

    private void verifyCode() {
        try {
            mViewModel.verifyCode();
        } catch (IllegalArgumentException ex) {
            CommonTasks.showToast(this, getString(R.string.verification_failed));
        }
    }
}
