package com.avaglir.abercrombiepromo;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by mammothbane on 9/7/2015.
 */

@Module
public class NetModule {
    @Provides NetService provideNetService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.abercrombie.com/anf/nativeapp/Feeds").addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(NetService.class);
    }
}
