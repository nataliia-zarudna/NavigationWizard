package com.zarudna.navigationwizard.dependency;

import com.zarudna.navigationwizard.ui.NavigationViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nsirobaba on 4/27/18.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(NavigationViewModel navigationViewModel);

}
