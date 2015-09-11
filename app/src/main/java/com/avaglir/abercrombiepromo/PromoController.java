package com.avaglir.abercrombiepromo;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.Response;

/**
 * Created by mammothbane on 9/4/2015.
 */
public class PromoController {
    @Inject NetService netService;
    @Inject MainActivity mMainActivity;
    @Inject Gson gson;
    private final ArrayList<Promo> mPromos = new ArrayList<>();

    @Inject
    public PromoController() {}

    public List<Promo> getPromos() {
        return mPromos;
    }

    /**
     * asynchronously request promo list to be updated from the feed
     */
    public void updatePromos() {
        netService.update().enqueue(new Callback<PromoListWrapper>() {
            @Override
            public void onResponse(Response<PromoListWrapper> response) {
                List<Promo> list = response.body().promotions;
                mergeIntoPromoList(list);
                mMainActivity.notifyRefreshCompleted();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(getClass().getSimpleName(), "failed to download new promos", t);
                mMainActivity.notifyRefreshCompleted();
            }
        });
    }

    private void mergeIntoPromoList(List<Promo> list) {
        if (list == null) return;
        boolean updated[] = new boolean[mPromos.size()]; // all false by default
        ArrayList<Promo> toAdd = new ArrayList<>();
        for (Promo p : list) {
            int existingPromo = mPromos.indexOf(p);
            if (existingPromo != -1 && !updated[existingPromo]) {
                mPromos.get(existingPromo).updateFrom(p);
                updated[existingPromo] = true;
            } else {
                toAdd.add(p);
            }
        }

        synchronized (mPromos) {
            for (int i = 0; i < updated.length; i++) {
                if (!updated[i]) mPromos.remove(i);
            }
            mPromos.addAll(toAdd);
        }
    }

    public static class PromoListWrapper {
        public List<Promo> promotions;
    }
}

