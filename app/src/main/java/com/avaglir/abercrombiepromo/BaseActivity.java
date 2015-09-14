package com.avaglir.abercrombiepromo;

import android.app.Activity;
import android.view.View;

/**
 * Created by mammothbane on 9/12/2015.
 */
public abstract class BaseActivity extends Activity {

    /**
     * status bar is transparent and doesn't affect layout in versions before lollipop.
     * we fill that space with a simple view here.
     */
    void expandCompatPlaceholderView(View v) {
        if (!Util.isAfterLollipop()) {
            //convert dp to pixels:
            v.getLayoutParams().height = Util.pixelsFromDp(25, v.getContext());
        }
    }

}
