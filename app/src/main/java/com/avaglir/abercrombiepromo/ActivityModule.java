package com.avaglir.abercrombiepromo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mammothbane on 9/8/2015.
 */
@Module
public class ActivityModule {
    private MainActivity mMainActivity;

    public ActivityModule(MainActivity activity) {
        this.mMainActivity = activity;
    }

    @Provides
    MainActivity provideMainActivity() { return mMainActivity; }
}
