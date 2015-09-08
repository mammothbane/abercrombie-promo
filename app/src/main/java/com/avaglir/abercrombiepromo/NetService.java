package com.avaglir.abercrombiepromo;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by mammothbane on 9/7/2015.
 */
public interface NetService {
    @Headers({ "User-Agent: Abercrombie Promo App by mammothbane", "Accept: application/json" })
    @GET("/promotions.json")
    Call<List<Promo>> update();
}
