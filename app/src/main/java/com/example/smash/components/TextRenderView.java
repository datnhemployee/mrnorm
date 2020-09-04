package com.example.smash.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;

import com.example.smash.R;

public class TextRenderView extends androidx.appcompat.widget.AppCompatTextView {
    Paint paint;
    Typeface font;
    Rect bounds = new Rect();
    public static final String DEAFULT_PATH_FONTS = "fonts/";
    public static final String DEFAULT_FONT = "SdThoseGoodTimesOfLife-B1An.ttf";
    public static final String FONT_METAMORPHOUS = "Metamorphous-7wZ4.ttf";
    public static final float DEFAULT_FONT_SCALE_HEADER_SIZE = 1.67f;
    public static final float DEFAULT_FONT_SCALE_TITLE_SIZE = DEFAULT_FONT_SCALE_HEADER_SIZE * 2;

    public TextRenderView(Context context) {
        super(context);
        this.paint = new Paint();
        this.font = Typeface.createFromAsset(
                context.getAssets(),
                TextRenderView.DEAFULT_PATH_FONTS +
                        TextRenderView.FONT_METAMORPHOUS
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255,255,255);
        this.paint.setColor(Color.YELLOW);
        this.paint.setTypeface(this.font);
        int fontSizeBody = (int)this.getResources().getDimensionPixelSize(
                R.dimen.font_size_body);
        int grid = (int)this.getResources().getDimensionPixelSize(
                R.dimen.fab_margin);
        int fontSizeTitle = (int) TextRenderView.DEFAULT_FONT_SCALE_TITLE_SIZE * fontSizeBody;
        this.paint.setTextSize(
                fontSizeTitle
        );
        this.paint.setTextAlign(
            Paint.Align.CENTER
        );
        canvas.drawText(
                "Smash!",
                canvas.getWidth()/2,
                canvas.getHeight()/2,
                this.paint);

        String text = "Welcom to fantasy world o_0";

        this.paint.setColor(Color.WHITE);
        this.paint.setTextSize(
            fontSizeBody
        );

        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.getTextBounds(
                text,
                0,
                text.length(),
                bounds
        );
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth((int)(fontSizeTitle * 0.05));
//        this.paint.setShadowLayer(
//                (float)(fontSizeTitle * 0.1),
//                1.5f,
//                1.3f,
//                Color.BLACK);
        canvas.drawText(
                text,
                canvas.getWidth()/2,
                canvas.getHeight()/2 + grid * 2,
                this.paint);


//        grid * 2 + fontSizeTitle + fontSizeBody,
        this.invalidate();
    }
}
