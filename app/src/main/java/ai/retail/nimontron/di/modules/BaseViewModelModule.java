package ai.retail.nimontron.di.modules;

import androidx.lifecycle.ViewModelProvider;

import ai.retail.nimontron.viewmodels.factories.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
abstract public class BaseViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
