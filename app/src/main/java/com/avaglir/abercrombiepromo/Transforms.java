package com.avaglir.abercrombiepromo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by mammothbane on 9/13/2015.
 */
public class Transforms {
    public static class ClipHeight implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            Bitmap result = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight() - 3);

            if (result != source) source.recycle();

            return result;
        }

        @Override
        public String key() {
            return "clip()";
        }
    }

    public static class GradientOverlay implements Transformation {
        int height;
        public GradientOverlay(int heightInPixels) {
            this.height = heightInPixels;
        }

        public GradientOverlay() { this.height = 30; }

        @Override
        public Bitmap transform(Bitmap src) {
            int w = src.getWidth();
            int h = src.getHeight();
            Bitmap overlay = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(overlay);

            canvas.drawBitmap(src, 0, 0, null);

            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0,  h - height, 0, h, 0x00000000, 0x88000000, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            canvas.drawRect(0, h - height, w, h, paint);

            src.recycle();

            return overlay;
        }

        @Override
        public String key() {
            return "gradient_" + height;
        }
    }
}
