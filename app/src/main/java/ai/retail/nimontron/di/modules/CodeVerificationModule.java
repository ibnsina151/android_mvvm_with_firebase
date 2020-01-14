package ai.retail.nimontron.di.modules;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import ai.retail.nimontron.activities.CodeVerificationActivity;
import ai.retail.nimontron.data.DataManager;
import ai.retail.nimontron.di.ViewModelKey;
import ai.retail.nimontron.domain.repositories.NumberVerificationRepo;
import ai.retail.nimontron.domain.repositories.impl.NumberVerificationRepoImpl;
import ai.retail.nimontron.viewmodels.CodeVerificationViewModel;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module(includes = {CodeVerificationModule.InnerModule.class, BaseViewModelModule.class})
public abstract class CodeVerificationModule {

    @Binds
    abstract Activity getActivity(CodeVerificationActivity codeVerificationActivity);

    @Binds
    @IntoMap
    @ViewModelKey(CodeVerificationViewModel.class)
    abstract ViewModel bindsCodeVerificationViewModel(CodeVerificationViewModel codeVerificationViewModel);

    @Module
    public static class InnerModule {
        @Provides
        FirebaseAuth getFirebaseAuth() {
            return FirebaseAuth.getInstance();
        }

        @Provides
        NumberVerificationRepo getNumberVerificationRepo(DataManager dataManager, FirebaseAuth auth, Activity activity) {
            return new NumberVerificationRepoImpl(dataManager, auth, activity);
        }
    }
}
