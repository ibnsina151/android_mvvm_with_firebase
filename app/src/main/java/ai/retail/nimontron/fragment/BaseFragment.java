package ai.retail.nimontron.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import ai.retail.nimontron.R;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseFragment<DB extends ViewDataBinding, AVM extends ViewModel> extends Fragment {

    protected DB mBinding;
    protected AVM mActivityViewModel;

    protected ProgressDialog progressDialog;
    protected AlertDialog.Builder mAlertDialogBuilder;
    private boolean isActivityRunning;


    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onAttach(Context context) {
        performDependencyInjection();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        isActivityRunning = true;
        return mBinding.getRoot();
    }

    public void performDependencyInjection() {
        //AndroidInjection.inject(this);
    }

    /*protected VM provideViewModel(Fragment fragment, Class<VM> modelClass) {
        return ViewModelProviders.of(fragment).get(modelClass);
    }*/

    protected AVM provideViewModel(FragmentActivity fragment, Class<AVM> modelClass) {
        return ViewModelProviders.of(fragment).get(modelClass);
    }

    public void showSnackBar(String message) {
        Snackbar.make(getActivity().getWindow().getDecorView(), message, Snackbar.LENGTH_SHORT).show();
    }
    public void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void showProgressDialog() {
        showProgressDialog("");
        //Unused import statement
    }


    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            initProgressLoader();
        }

        progressDialog.setMessage(message);
        if (isActivityRunning) progressDialog.show();
    }
    public void initProgressLoader() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }


    /**
     * Cancel progress dialog.
     */
    public void cancelProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        isActivityRunning = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isActivityRunning = false;
    }
}
