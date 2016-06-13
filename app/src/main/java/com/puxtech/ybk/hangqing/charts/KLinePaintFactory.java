package com.puxtech.ybk.hangqing.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;

import com.puxtech.ybk.R;

/**
 * Created by fanshuo on 16/4/28.
 */
public class KLinePaintFactory {

    Context context;
    Paint linePaint;// 边框
    Paint linePaintDashed;// 边框虚线
    Paint redTextPaint;// 红字
    Paint greenTextPaint;// 绿字
    Paint whiteTextPaint;// 白字
    Paint whiteTextPaintRight;// 白字，右对齐
    Paint pricePaint;// 价格线
    Paint priceFillPaint;// 价格填充颜色
    Paint averagePricePaint;// 平均价格线
    Paint leftTextPaintWhite,leftTextPaintRed,leftTextPaintGreen,leftTextPaintYellow;//左边文字
    Paint pma5Paint;// pma5线
    Paint pma10Paint;// pma10线
    Paint pma30Paint;// pma30线
    Paint pma5TextPaint;
    Paint pma10TextPaint;
    Paint pma30TextPaint;
    Paint difPaint;
    Paint deaPaint;
    Paint macdPaint;
    Paint difTextPaint;
    Paint deaTextPaint;
    Paint macdTextPaint;
    Paint crossLinePaint;// 十字线
    Paint volumeTextPaint;// 成交量文字
    Paint bottomTextPaint;// 底部时间文字
    Paint bottomTextRightAlignPaint;// 底部时间文字，右对齐
    float topTextSize,leftTextSize,volumeTextSize, indexTextSize,bottomTexSize;

    public KLinePaintFactory(Context context) {
        this.context = context;
        initPaint();
    }

    private void initPaint() {

        topTextSize = ChartsScreenSizeUtil.Dp2Px(context, 10);
        leftTextSize = ChartsScreenSizeUtil.Dp2Px(context, 12);
        float dp1 = ChartsScreenSizeUtil.Dp2Px(context, 1);
        volumeTextSize = ChartsScreenSizeUtil.Dp2Px(context, 10);
        indexTextSize = ChartsScreenSizeUtil.Dp2Px(context, 10);
        bottomTexSize = ChartsScreenSizeUtil.Dp2Px(context, 10);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(context.getResources().getColor(
                R.color.fenshi_frame_line_dash));
        linePaint.setStrokeWidth(1);

        linePaintDashed = new Paint();
        linePaintDashed.setAntiAlias(true);
        linePaintDashed.setColor(context.getResources().getColor(
                R.color.fenshi_frame_line_dash));
        linePaintDashed.setStrokeWidth(1);
        PathEffect effects = new DashPathEffect(new float[] { 10, 10, 10, 10 }, 1);
        linePaintDashed.setPathEffect(effects);

        redTextPaint = new Paint();
        redTextPaint.setAntiAlias(true);
        redTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_red_text));
        redTextPaint.setTextAlign(Paint.Align.RIGHT);
        redTextPaint.setStrokeWidth(1);
        redTextPaint.setTextSize(20);

        greenTextPaint = new Paint();
        greenTextPaint.setAntiAlias(true);
        greenTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_green_text));
        greenTextPaint.setStrokeWidth(1);
        greenTextPaint.setTextAlign(Paint.Align.RIGHT);
        greenTextPaint.setTextSize(20);

        whiteTextPaint = new Paint();
        whiteTextPaint.setAntiAlias(true);
        whiteTextPaint.setColor(Color.WHITE);
        whiteTextPaint.setStrokeWidth(1);
        whiteTextPaint.setTextAlign(Paint.Align.RIGHT);
        whiteTextPaint.setTextSize(20);

        whiteTextPaintRight = new Paint();
        whiteTextPaintRight.setAntiAlias(true);
        whiteTextPaintRight.setColor(Color.WHITE);
        whiteTextPaintRight.setStrokeWidth(1);
        whiteTextPaintRight.setTextAlign(Paint.Align.LEFT);
        whiteTextPaintRight.setTextSize(20);

        pricePaint = new Paint();
        pricePaint.setColor(context.getResources().getColor(
                R.color.fenshi_price_line));
        pricePaint.setAntiAlias(true);
        pricePaint.setFilterBitmap(true);
        pricePaint.setStrokeWidth(1.0f);
        pricePaint.setStyle(Paint.Style.STROKE);
//        Shader shader = new LinearGradient(0, 0, 0, 500, context.getResources().getColor(android.R.color.holo_blue_dark),  context.getResources().getColor(R.color.white), Shader.TileMode.CLAMP);
//        pricePaint.setShader(shader);

        priceFillPaint = new Paint();
        priceFillPaint.setColor(context.getResources().getColor(
                R.color.fenshi_price_fill));
        priceFillPaint.setStrokeWidth(1.0f);
        priceFillPaint.setStyle(Paint.Style.FILL);

        averagePricePaint = new Paint();
        averagePricePaint.setColor(context.getResources().getColor(
                R.color.hangqing_yellow_text));
        averagePricePaint.setAntiAlias(true);
        averagePricePaint.setStrokeWidth(1.0f);
        averagePricePaint.setStyle(Paint.Style.STROKE);


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
        leftTextPaintRed.setTextSize(20);

        leftTextPaintGreen = new Paint();
        leftTextPaintGreen.setAntiAlias(true);
        leftTextPaintGreen.setColor(context.getResources().getColor(
                R.color.hangqing_green_text));
        leftTextPaintGreen.setStrokeWidth(1);
        leftTextPaintGreen.setTextAlign(Paint.Align.RIGHT);
        leftTextPaintGreen.setTextSize(20);

        leftTextPaintYellow = new Paint();
        leftTextPaintYellow.setAntiAlias(true);
        leftTextPaintYellow.setColor(context.getResources().getColor(
                R.color.hangqing_yellow_text));
        leftTextPaintYellow.setStrokeWidth(1);
        leftTextPaintYellow.setTextAlign(Paint.Align.RIGHT);
        leftTextPaintYellow.setTextSize(leftTextSize);


        pma5Paint = new Paint();
        pma5Paint.setAntiAlias(true);
        pma5Paint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_pma5));
        pma5Paint.setStrokeWidth(1);
        pma5Paint.setStyle(Paint.Style.STROKE);

        pma10Paint = new Paint();
        pma10Paint.setAntiAlias(true);
        pma10Paint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_pma10));
        pma10Paint.setStrokeWidth(1);
        pma10Paint.setStyle(Paint.Style.STROKE);

        pma30Paint = new Paint();
        pma30Paint.setAntiAlias(true);
        pma30Paint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_pma30));
        pma30Paint.setStrokeWidth(1);
        pma30Paint.setStyle(Paint.Style.STROKE);

        pma5TextPaint = new Paint();
        pma5TextPaint.setAntiAlias(true);
        pma5TextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_pma5));
        pma5TextPaint.setTextSize(topTextSize);

        pma10TextPaint = new Paint();
        pma10TextPaint.setAntiAlias(true);
        pma10TextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_pma10));
        pma10TextPaint.setTextSize(topTextSize);

        pma30TextPaint = new Paint();
        pma30TextPaint.setAntiAlias(true);
        pma30TextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_pma30));
        pma30TextPaint.setTextSize(topTextSize);

        difPaint = new Paint();
        difPaint.setAntiAlias(true);
        difPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_dif));
        difPaint.setStrokeWidth(1);
        difPaint.setStyle(Paint.Style.STROKE);

        deaPaint = new Paint();
        deaPaint.setAntiAlias(true);
        deaPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_dea));
        deaPaint.setStrokeWidth(1);
        deaPaint.setStyle(Paint.Style.STROKE);

        macdPaint = new Paint();
        macdPaint.setAntiAlias(true);
        macdPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_macd));
        macdPaint.setStrokeWidth(1);
        macdPaint.setStyle(Paint.Style.STROKE);

        difTextPaint = new Paint();
        difTextPaint.setAntiAlias(true);
        difTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_dif));
        difTextPaint.setTextSize(indexTextSize);

        deaTextPaint = new Paint();
        deaTextPaint.setAntiAlias(true);
        deaTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_dea));
        deaTextPaint.setTextSize(indexTextSize);

        macdTextPaint = new Paint();
        macdTextPaint.setAntiAlias(true);
        macdTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_kline_macd));
        macdTextPaint.setTextSize(indexTextSize);

        crossLinePaint = new Paint();
        crossLinePaint.setAntiAlias(true);
        crossLinePaint.setColor(context.getResources().getColor(R.color.hangqing_cross_line));
        crossLinePaint.setStyle(Paint.Style.STROKE);
        PathEffect crossLineEffects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
        crossLinePaint.setPathEffect(crossLineEffects);
        crossLinePaint.setStrokeWidth(2);

        volumeTextPaint = new Paint();
        volumeTextPaint.setAntiAlias(true);
        volumeTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_yellow_text));
        volumeTextPaint.setStrokeWidth(1);
        volumeTextPaint.setTextAlign(Paint.Align.LEFT);
        volumeTextPaint.setTextSize(volumeTextSize);

        bottomTextPaint = new Paint();
        bottomTextPaint.setAntiAlias(true);
        bottomTextPaint.setColor(context.getResources().getColor(
                R.color.hangqing_white_text));
        bottomTextPaint.setTextAlign(Paint.Align.LEFT);
        bottomTextPaint.setTextSize(volumeTextSize);

        bottomTextRightAlignPaint = new Paint();
        bottomTextRightAlignPaint.setAntiAlias(true);
        bottomTextRightAlignPaint.setColor(context.getResources().getColor(
                R.color.hangqing_white_text));
        bottomTextRightAlignPaint.setTextAlign(Paint.Align.RIGHT);
        bottomTextRightAlignPaint.setTextSize(volumeTextSize);

    }
}
