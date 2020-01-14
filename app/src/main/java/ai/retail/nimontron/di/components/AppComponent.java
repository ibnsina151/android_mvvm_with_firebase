package ai.retail.nimontron.di.components;

import android.app.Application;

import ai.retail.nimontron.NimontronApplication;
import ai.retail.nimontron.di.ApplicationScope;
import ai.retail.nimontron.di.builders.ActivityBuilderModule;
import ai.retail.nimontron.di.modules.AppModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@ApplicationScope
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(NimontronApplication app);
}
