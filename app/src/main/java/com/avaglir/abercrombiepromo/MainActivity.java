package com.avaglir.abercrombiepromo;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private TextView mAbercrombieTitle;
    private TextView mNothingHere;

    private View mCompatPlaceholderView;

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

        /**
         * status bar is transparent and doesn't affect layout in versions before lollipop.
         * we fill out the space with a simple view here.
         */
        mCompatPlaceholderView = findViewById(R.id.compat_statusbar_placeholder);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //convert dp to pixels:
            mCompatPlaceholderView.getLayoutParams().height = ((int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics())
            );
        }

        mAbercrombieTitle = (TextView) findViewById(R.id.tv_abercrombie_title);
        Typeface garamondBold = Typeface.createFromAsset(getAssets(), "fonts/adobe_garamond_bold.ttf");
        mAbercrombieTitle.setTypeface(garamondBold);

        mNothingHere = ((TextView) findViewById(R.id.tv_nothing_here));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        mRecyclerView.setHasFixedSize(true); //slight optimization

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

}
