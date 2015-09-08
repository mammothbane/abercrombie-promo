package com.avaglir.abercrombiepromo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by mammothbane on 9/7/2015.
 */
public interface NetService {
    @Headers({ "User-Agent: Abercrombie Promo App by mammothbane", "Accept: application/json" })
    @GET("/anf/nativeapp/Feeds/promotions.json")
    Call<PromoController.PromoListWrapper> update();
}
