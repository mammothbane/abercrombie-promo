package com.avaglir.abercrombiepromo;

import dagger.Component;

/**
 * Created by mammothbane on 9/7/2015.
 */
@Component(modules = { NetModule.class, ActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    PromoController promoController();
}
