package ai.retail.nimontron.di.modules;


import androidx.lifecycle.ViewModel;

import ai.retail.nimontron.di.ViewModelKey;
import ai.retail.nimontron.viewmodels.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(includes = BaseViewModelModule.class)
public abstract class SignInModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    abstract ViewModel bindsSignInViewModel(AuthViewModel authViewModel);

}
