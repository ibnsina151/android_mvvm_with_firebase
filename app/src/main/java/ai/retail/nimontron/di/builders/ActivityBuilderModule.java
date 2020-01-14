package ai.retail.nimontron.di.builders;


import ai.retail.nimontron.activities.CodeVerificationActivity;
import ai.retail.nimontron.activities.SignInActivity;
import ai.retail.nimontron.di.modules.CodeVerificationModule;
import ai.retail.nimontron.di.modules.SignInModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = SignInModule.class)
    abstract SignInActivity bindSignInActivity();

    @ContributesAndroidInjector(modules = CodeVerificationModule.class)
    abstract CodeVerificationActivity bindCodeVerificationActivity();


//    @ContributesAndroidInjector(modules = ImageMappingModule.class)
//    abstract ImageMappingActivity bindsImageMappingActivity();

}
