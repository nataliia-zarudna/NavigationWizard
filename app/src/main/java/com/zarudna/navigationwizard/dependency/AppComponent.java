package com.zarudna.navigationwizard.dependency;

import com.zarudna.navigationwizard.model.persistence.RoomModule;
import com.zarudna.navigationwizard.ui.NavigationViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component
 */

@Component(modules = {AppModule.class, RoomModule.class})
@Singleton
public interface AppComponent {

    void inject(NavigationViewModel navigationViewModel);

}
