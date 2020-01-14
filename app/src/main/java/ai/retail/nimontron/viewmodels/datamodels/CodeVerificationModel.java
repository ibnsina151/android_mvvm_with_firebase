package ai.retail.nimontron.viewmodels.datamodels;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class CodeVerificationModel extends BaseObservable {
    private String code, phoneNumber;
    private boolean isValid;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (TextUtils.equals(this.code, code)) return;
        this.code = code;
        setValid(checkValidity());
    }

    private boolean checkValidity() {
        return !TextUtils.isEmpty(code) && code.length() == 6;
    }

    @Bindable
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        if (isValid == valid) return;
        isValid = valid;
        notifyPropertyChanged(BR.valid);
    }
}

