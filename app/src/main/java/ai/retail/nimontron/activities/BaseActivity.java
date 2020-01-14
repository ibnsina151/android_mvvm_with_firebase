package ai.retail.nimontron.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import ai.retail.nimontron.R;
import ai.retail.nimontron.helper.CrashlyticsHelper;
import ai.retail.nimontron.utils.CommonTasks;
import ai.retail.nimontron.utils.Constants;
import ai.retail.nimontron.viewmodels.BaseViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    /* Primary toolbar*/
    protected Toolbar toolbar;
    protected ProgressDialog progressDialog;
    protected AlertDialog.Builder mAlertDialogBuilder;
    protected ActionBar actionBar;
    protected DB mBinding;
    protected VM mViewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    CrashlyticsHelper mCrashlyticsHelper;
    private boolean isActivityRunning;

    protected abstract
    @LayoutRes
    int getLayoutRes();

    /*@Inject
    DispatchingAndroidInjector<Fragment> mSupportFragmentInjector;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        mAlertDialogBuilder = new AlertDialog.Builder(this);
        isActivityRunning = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityRunning = true;
    }

    /**
     * Set toolbar into actionbar.
     */

    protected void setupToolbar(int id) {
        if (toolbar == null) {
            toolbar = findViewById(id);
            toolbar.setTitleTextColor(Color.WHITE);
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

    }

    protected void setupToolbar(int toolbar, String title) {
        setupToolbar(toolbar);
        actionBar.setTitle(title);
    }

    /**
     * Initialize the loader for Child class whenever necessary.
     */

    public void initProgressLoader() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    /**
     * Initialize the loader for Child class whenever necessary.
     */

    public void initProgressLoader(boolean isCancelable) {
        initProgressLoader();
        progressDialog.setCancelable(isCancelable);
    }

    /**
     * Sets whether this dialog is cancelable with the
     */

    protected void setProgressCancelable(boolean isCancelable) {
        if (progressDialog != null) {
            progressDialog.setCancelable(isCancelable);
        }
    }

    protected void showTerminationAlert(String body, final OnTerminationAcceptedListener onTerminationAcceptedListener) {
        mAlertDialogBuilder.setCancelable(false);
        mAlertDialogBuilder.setTitle(Constants.EMPTY_STRING);
        mAlertDialogBuilder.setMessage(body);
        mAlertDialogBuilder.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (onTerminationAcceptedListener != null)
                    onTerminationAcceptedListener.onComplete();
            }
        });

        if (isActivityRunning) mAlertDialogBuilder.show();
    }


    protected void showProgressDialog() {
        showProgressDialog(getString(R.string.text_please_wait));
    }

    /**
     * Show progress dialog.
     *
     * @param message The message show in the progress dialog initially.
     */

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            initProgressLoader();
        }

        progressDialog.setMessage(message);
        if (isActivityRunning) progressDialog.show();
    }

    /**
     * Cancel progress dialog.
     */

    public void cancelProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void showSnackBar(String message) {
        Snackbar.make(getWindow().getDecorView(), message, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public boolean isOnline() {
        return CommonTasks.isOnline(this);
    }

    public void setCrashlyticsUserCred(String userName, String userEmail, String userId) {
        mCrashlyticsHelper.setUserEmail(userEmail);
        mCrashlyticsHelper.setUserIdentifier(userId);
        mCrashlyticsHelper.setUserName(userName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    protected VM provideViewModel(FragmentActivity fragment, Class<VM> modelClass) {
        return ViewModelProviders.of(fragment, viewModelFactory).get(modelClass);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityRunning = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelProgressDialog();
    }

    public interface OnTerminationAcceptedListener {
        void onComplete();
    }
}
