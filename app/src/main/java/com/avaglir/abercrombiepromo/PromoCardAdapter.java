package com.avaglir.abercrombiepromo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mammothbane on 9/4/2015.
 */
public class PromoCardAdapter extends RecyclerView.Adapter<PromoCardAdapter.ViewHolder> {
    private List<Promo> mPromoList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public ViewHolder(CardView view) {
            super(view);
            mCardView = view;
        }
    }

    public PromoCardAdapter(List<Promo> promoList) {
        mPromoList = promoList;
    }

    public PromoCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ll_card, parent, false);

        return new ViewHolder((CardView)v);
    }

    @Override
    public void onBindViewHolder(PromoCardAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPromoList.size();
    }
}
