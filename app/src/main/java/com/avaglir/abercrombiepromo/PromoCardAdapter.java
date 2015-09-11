package com.avaglir.abercrombiepromo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mammothbane on 9/4/2015.
 */
public class PromoCardAdapter extends RecyclerView.Adapter<PromoCardAdapter.ViewHolder> {
    private final List<Promo> mPromoList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View view) {
            super(view);
            mTextView = ((TextView) view.findViewById(R.id.tv_title));
            mImageView = ((ImageView) view.findViewById(R.id.iv_card));
        }
    }

    public PromoCardAdapter(final List<Promo> promoList) {
        mPromoList = promoList;
    }

    public PromoCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ll_card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PromoCardAdapter.ViewHolder holder, int pos) {
        Promo promo;
        synchronized (mPromoList) {
            promo = mPromoList.get(pos);
        }
        holder.mTextView.setText(promo.getTitle());
        Picasso.with(holder.mImageView.getContext())
                .load(promo.getImage())
                .fit()
                .centerCrop()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mPromoList.size();
    }
}
