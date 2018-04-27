package com.zarudna.navigationwizard.dependency;

import com.zarudna.navigationwizard.ui.MainViewModel;

import dagger.Component;

/**
 * Created by nsirobaba on 4/27/18.
 */

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainViewModel mainViewModel);

}
