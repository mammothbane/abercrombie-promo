package com.avaglir.abercrombiepromo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<Promo> mPromoList;

    @Inject
    PromoController promoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .netModule(new NetModule())
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        mRecyclerView.setHasFixedSize(true); //todo: idk if they're fixed or not

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPromoList = promoController.getPromos();
        mAdapter = new PromoCardAdapter(mPromoList);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout = ((SwipeRefreshLayout) findViewById(R.id.srl_main));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                promoController.updatePromos();
            }
        });
    }

    public void notifyRefreshCompleted() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
