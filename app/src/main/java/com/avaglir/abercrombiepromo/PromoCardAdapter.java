package com.avaglir.abercrombiepromo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mammothbane on 9/4/2015.
 */
public class PromoCardAdapter extends RecyclerView.Adapter<PromoCardAdapter.ViewHolder> {
    private final List<Promo> mPromoList;
    private final Activity mActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title) public TextView mTextView;
        @Bind(R.id.iv_card) public ImageView mImageView;
        @Bind(R.id.ll_card) public LinearLayout mLinearLayout;
        @Bind(R.id.gradient_overlay) public View mOverlayView;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public PromoCardAdapter(final List<Promo> promoList, final Activity activity) {
        mPromoList = promoList;
        this.mActivity = activity;
    }

    public PromoCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ll_card, parent, false);
        ((CardView) v.findViewById(R.id.cv_item)).setPreventCornerOverlap(false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PromoCardAdapter.ViewHolder holder, int pos) {
        final Promo promo;
        synchronized (mPromoList) {
            promo = mPromoList.get(pos);
        }
        holder.mTextView.setText(promo.getTitle());
        List<Transformation> transforms = new ArrayList<>();
        transforms.add(new Transforms.ClipHeight());
        transforms.add(new Transforms.GradientOverlay(Util.pixelsFromDp(80, mActivity)));
        Picasso.with(mActivity)
                .load(promo.getImage())
                .transform(transforms)
                .fit()
                .centerCrop()
                .placeholder(ResourcesCompat.getDrawable(mActivity.getResources(), R.drawable.background_grey, null))
                .into(holder.mImageView);

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, PromoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("promo", promo);
                intent.putExtras(bundle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity,
                            Pair.create(((View) holder.mTextView), "title"),
                            Pair.create(((View) holder.mImageView), "image"),
                            Pair.create(((View) holder.mImageView), "overlay"));
                    mActivity.startActivity(intent, options.toBundle());
                } else {
                    mActivity.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mPromoList.size();
    }
}
