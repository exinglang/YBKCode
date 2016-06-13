package com.puxtech.ybk.hangqing.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Shader;

import com.puxtech.ybk.R;

/**
 * Created by fanshuo on 16/4/28.
 */
public class FenShiPaintFactory {

    Context context;
    Paint linePaint;// 边框
    Paint linePaintDashed;// 边框虚线
    Paint redTextPaint;// 红字
    Paint greenTextPaint;// 绿字
    Paint whiteTextPaint;// 白字
    Paint topTextPaint;// 顶部文字
    Paint volumeTextPaint;// 成交量文字
    Paint whiteTextPaintRight;// 白字，右对齐
    Paint pricePaint;// 价格线
    Paint priceFillPaint;// 价格填充颜色
    Paint averagePricePaint;// 平均价格线
    Paint leftTextPaintWhite,leftTextPaintRed,leftTextPaintGreen,leftTextPaintYellow;//左边文字
    Paint crossLinePaint;// 十字线
    float leftTextSize,topTextSize,volumeTextSize;

    public FenShiPaintFactory(Context context) {
        this.context = context;
        initPaint();
    }

    private void initPaint() {

        leftTextSize = ChartsScreenSizeUtil.Dp2Px(context, 12);
        topTextSize = ChartsScreenSizeUtil.Dp2Px(context, 12);
        volumeTextSize = ChartsScreenSizeUtil.Dp2Px(context, 10);

        this.linePaint = new Paint();
        this.linePaint.setAntiAlias(true);
        this.linePaint.setColor(context.getResources().getColor(
                R.color.fenshi_frame_line_dash));
        this.linePaint.setStrokeWidth(1);

        this.linePaintDashed = new Paint();
        this.linePaintDashed.setAntiAlias(true);
        this.linePaintDashed.setColor(context.getResources().getColor(
                R.color.fenshi_frame_line_dash));
        this.linePaintDashed.setStrokeWidth(1);
        PathEffect effects = new DashPathEffect(new float[] { 10, 10, 10, 10 }, 1);
        this.linePaintDashed.setPathEffect(effects);

        this.redTextPaint = new Paint();
        this.redTextPaint.setAntiAlias(true);
        this.redTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_red_text));
        this.redTextPaint.setTextAlign(Paint.Align.RIGHT);
        this.redTextPaint.setStrokeWidth(1);
        this.redTextPaint.setTextSize(20);

        this.greenTextPaint = new Paint();
        this.greenTextPaint.setAntiAlias(true);
        this.greenTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_green_text));
        this.greenTextPaint.setStrokeWidth(1);
        this.greenTextPaint.setTextAlign(Paint.Align.RIGHT);
        this.greenTextPaint.setTextSize(20);

        this.whiteTextPaint = new Paint();
        this.whiteTextPaint.setAntiAlias(true);
        this.whiteTextPaint.setColor(Color.WHITE);
        this.whiteTextPaint.setStrokeWidth(1);
        this.whiteTextPaint.setTextAlign(Paint.Align.RIGHT);
        this.whiteTextPaint.setTextSize(20);

        this.whiteTextPaintRight = new Paint();
        this.whiteTextPaintRight.setAntiAlias(true);
        this.whiteTextPaintRight.setColor(Color.WHITE);
        this.whiteTextPaintRight.setStrokeWidth(1);
        this.whiteTextPaintRight.setTextAlign(Paint.Align.LEFT);
        this.whiteTextPaintRight.setTextSize(20);

        this.topTextPaint = new Paint();
        this.topTextPaint.setAntiAlias(true);
        this.topTextPaint.setColor(Color.WHITE);
        this.topTextPaint.setStrokeWidth(1);
        this.topTextPaint.setTextAlign(Paint.Align.LEFT);
        this.topTextPaint.setTextSize(topTextSize);

        this.volumeTextPaint = new Paint();
        this.volumeTextPaint.setAntiAlias(true);
        this.volumeTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_yellow_text));
        this.volumeTextPaint.setStrokeWidth(1);
        this.volumeTextPaint.setTextAlign(Paint.Align.LEFT);
        this.volumeTextPaint.setTextSize(volumeTextSize);

        this.pricePaint = new Paint();
        this.pricePaint.setColor(context.getResources().getColor(
                R.color.fenshi_price_line));
        this.pricePaint.setAntiAlias(true);
        this.pricePaint.setFilterBitmap(true);
        this.pricePaint.setStrokeWidth(1.0f);
        this.pricePaint.setStyle(Paint.Style.STROKE);
//        Shader shader = new LinearGradient(0, 0, 0, 500, context.getResources().getColor(android.R.color.holo_blue_dark),  context.getResources().getColor(R.color.white), Shader.TileMode.CLAMP);
//        pricePaint.setShader(shader);

        this.priceFillPaint = new Paint();
        this.priceFillPaint.setColor(context.getResources().getColor(
                R.color.fenshi_price_fill));
        this.priceFillPaint.setStrokeWidth(1.0f);
        this.priceFillPaint.setStyle(Paint.Style.FILL);

        this.averagePricePaint = new Paint();
        this.averagePricePaint.setColor(context.getResources().getColor(
                R.color.hangqing_yellow_text));
        this.averagePricePaint.setAntiAlias(true);
        this.averagePricePaint.setStrokeWidth(1.0f);
        this.averagePricePaint.setStyle(Paint.Style.STROKE);

        leftTextPaintWhite = new Paint();
        leftTextPaintWhite.setAntiAlias(true);
        leftTextPaintWhite.setColor(Color.WHITE);
        leftTextPaintWhite.setStrokeWidth(1);
        leftTextPaintWhite.setTextAlign(Paint.Align.RIGHT);
        leftTextPaintWhite.setTextSize(leftTextSize);

        leftTextPaintRed = new Paint();
        leftTextPaintRed.setAntiAlias(true);
        leftTextPaintRed.setColor(context.getResources().getColor(
                R.color.hangqing_red_text));
        leftTextPaintRed.setStrokeWidth(1);
        leftTextPaintRed.setTextAlign(Paint.Align.RIGHT);
        leftTextPaintRed.setTextSize(leftTextSize);

        leftTextPaintGreen = new Paint();
        leftTextPaintGreen.setAntiAlias(true);
        leftTextPaintGreen.setColor(context.getResources().getColor(
                R.color.hangqing_green_text));
        leftTextPaintGreen.setStrokeWidth(1);
        leftTextPaintGreen.setTextAlign(Paint.Align.RIGHT);
        leftTextPaintGreen.setTextSize(leftTextSize);

        leftTextPaintYellow = new Paint();
        leftTextPaintYellow.setAntiAlias(true);
        leftTextPaintYellow.setColor(context.getResources().getColor(
                R.color.hangqing_yellow_text));
        leftTextPaintYellow.setStrokeWidth(1);
        leftTextPaintYellow.setTextAlign(Paint.Align.RIGHT);
        leftTextPaintYellow.setTextSize(leftTextSize);

        this.crossLinePaint = new Paint();
        this.crossLinePaint.setAntiAlias(true);
        this.crossLinePaint.setColor(context.getResources().getColor(R.color.hangqing_cross_line));
        this.crossLinePaint.setStyle(Paint.Style.STROKE);
        PathEffect crossLineEffects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
        this.crossLinePaint.setPathEffect(crossLineEffects);
        this.crossLinePaint.setStrokeWidth(2);

    }
}
