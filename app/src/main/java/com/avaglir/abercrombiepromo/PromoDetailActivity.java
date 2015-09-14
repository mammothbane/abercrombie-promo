package com.avaglir.abercrombiepromo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mammothbane on 9/11/2015.
 */
public class PromoDetailActivity extends BaseActivity {
    @Bind(R.id.iv_main_image) ImageView mImageView;
    @Bind(R.id.tv_title) TextView mTitle;
    @Bind(R.id.tv_description) TextView mDescription;
    @Bind(R.id.bt_main) Button mButton;
    @Bind(R.id.compat_statusbar_placeholder) View mCompatPlaceholderView;

    private Promo promo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (Util.isAfterLollipop()) {
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
        }

        expandCompatPlaceholderView(mCompatPlaceholderView);

        Intent intent = getIntent();
        promo = ((Promo) intent.getExtras().getSerializable("promo"));

        List<Transformation> transforms = new ArrayList<>();
        transforms.add(new Transforms.ClipHeight());
        transforms.add(new Transforms.GradientOverlay(Util.pixelsFromDp(80, this)));

        Picasso.with(this).load(promo.image)
                .transform(transforms)
                .fit()
                .centerCrop()
                .into(mImageView);
        mTitle.setText(promo.title);
        mDescription.setText(promo.description);

    }

    @Override
    public void onBackPressed() {
        close();
    }

    private void close() {
        if (Util.isAfterLollipop()) finishAfterTransition();
        else finish();
    }
}
