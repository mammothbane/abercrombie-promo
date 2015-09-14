package com.avaglir.abercrombiepromo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rv_main) RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.srl_main) SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.tv_abercrombie_title) TextView mAbercrombieTitle;
    @Bind(R.id.tv_nothing_here) TextView mNothingHere;

    @Bind(R.id.compat_statusbar_placeholder) View mCompatPlaceholderView;

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

        ButterKnife.bind(this);

        if (Util.isAfterLollipop()) {
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
        }

        expandCompatPlaceholderView(mCompatPlaceholderView);

        Typeface garamondBold = Typeface.createFromAsset(getAssets(), "fonts/adobe_garamond_bold.ttf");
        mAbercrombieTitle.setTypeface(garamondBold);

        mRecyclerView.setHasFixedSize(true); //slight optimization

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPromoList = promoController.getPromos();
        mAdapter = new PromoCardAdapter(mPromoList, this);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                promoController.updatePromos();
            }
        });
        mSwipeRefreshLayout.setNestedScrollingEnabled(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.accent_material_dark);
    }

    public void notifyRefreshCompleted() {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
        if (!mPromoList.isEmpty()) {
            mNothingHere.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private List<Promo> generatePromos(int count) {
        ArrayList<Promo> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Promo(null, null, "" + i, "" + i, null, null));
        }
        return list;
    }

}
