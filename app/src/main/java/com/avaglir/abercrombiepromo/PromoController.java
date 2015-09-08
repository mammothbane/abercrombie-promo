package com.avaglir.abercrombiepromo;

import android.util.Log;

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
        netService.update().enqueue(new Callback<List<Promo>>() {
            @Override
            public void onResponse(Response<List<Promo>> response) {
                mergeIntoPromoList(response.body());
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
        boolean indices[] = new boolean[mPromos.size()]; // all false by default
        ArrayList<Promo> toAdd = new ArrayList<>();
        for (Promo p : list) {
            int existingPromo = mPromos.indexOf(p);
            if (existingPromo != -1) {
                mPromos.get(existingPromo).updateFrom(p);
                indices[existingPromo] = true;
            } else {
                toAdd.add(p);
            }
        }

        synchronized (mPromos) {
            for (int i = 0; i < indices.length; i++) {
                if (!indices[i]) mPromos.remove(i);
            }
            mPromos.addAll(toAdd);
        }
    }
}

