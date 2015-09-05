package com.avaglir.abercrombiepromo;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by mammothbane on 9/4/2015.
 */
public class PromoController {
    private static PromoController promoController;
    private AbercrombieService netService;
    private final Object writeLock = new Object();
    private ArrayList<Promo> promos = new ArrayList<>();

    private PromoController() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.abercrombie.com/anf/nativeapp/Feeds").build();
        netService = retrofit.create(AbercrombieService.class);
    }

    public static PromoController getPromoController() {
        if (promoController == null) promoController = new PromoController();
        return promoController;
    }

    public List<Promo> getPromos() {
        return promos;
    }

    /**
     * asynchronously request promo list to be updated from the feed
     */
    public void updatePromos() {

    }
}

interface AbercrombieService {
    @Headers({ "User-Agent: Abercrombie Promo App by mammothbane", "Accept: application/json" })
    @GET("/promotions.json")
    Call<List<Promo>> getPromos();
}