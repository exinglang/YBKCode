package com.puxtech.ybk.hangqing.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;

import com.puxtech.ybk.R;

import java.util.List;

/**
 * Created by fanshuo on 16/4/28.
 */
public class MiniFenShiView extends View {

    private static final int ROW_NUM = 6;//行数
    private static final int COLUMN_NUM = 4;//列数
    private static final int MARGIN = 10;//上下左右边距
    private Context context;
    private String[] bottomTimeArray;//底部时间
    private FenShiPaintFactory paintFactory;//画笔工厂
    private float fenShiStartX,fenShiStartY,fenShiEndX,fenShiEndY;
    private float volumeStartX,volumeStartY,volumeEndX,volumeEndY;
    private float rowHeight;//一行所占高度
    private TradeTimeManager tradeTimeManager;
    private FenShiChartModel fenShiChartModel;

    public MiniFenShiView(Context context) {
        super(context);
        init(context);
    }

    public MiniFenShiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MiniFenShiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化本类需要的信息
     */
    private void init(Context mContext){
        this.context = mContext;
        paintFactory = new FenShiPaintFactory(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(tradeTimeManager == null){
            return;
//            tradeTimeManager = new TradeTimeManager(1462237200000l,1462276800000l);
        }
        initData();
        drawFrame(canvas);
        if(fenShiChartModel != null){
            drawPrice(canvas);
            drawAveragePrice(canvas);
            drawVolume(canvas);
        }
    }

    private void initData() {
        float viewWidth = getMeasuredWidth();
        float viewHeight = getMeasuredHeight();
        //一行的高度
        rowHeight = (viewHeight-MARGIN*2)/(float)ROW_NUM;

        fenShiStartX = 0+MARGIN;
        fenShiStartY = 0+MARGIN;
        fenShiEndX = viewWidth-MARGIN;
        fenShiEndY = fenShiStartY + rowHeight*(ROW_NUM-2);

        volumeStartX = 0+MARGIN;
        volumeStartY = fenShiEndY;
        volumeEndX = viewWidth-MARGIN;
        volumeEndY = viewHeight-MARGIN;
    }

    private void drawFrame(Canvas canvas){
        float startX, startY, endX, endY;
        startX = fenShiStartX;
        startY = fenShiStartY;
        endX = volumeEndX;
        endY = volumeEndY;
        float ySpacing = (endY - startY) / ROW_NUM;// 行间隔
        float curY = startY;// 画横线时，当前这条线的y坐标
        // 画横线
        for (int i = 0; i < (ROW_NUM + 1); i++) {
            if (i == 0 || i == ROW_NUM) {
                canvas.drawLine(startX, curY, endX, curY, paintFactory.linePaint);
            } else {
                canvas.drawLine(startX, curY, endX, curY, paintFactory.linePaintDashed);
            }
            curY = curY + ySpacing;
        }
        //画竖线
        float xSpacing = (endX - startX)/COLUMN_NUM;//列间隔
        float curX = startX;
        for (int j = 0; j < (COLUMN_NUM + 1); j++) {
            if(j == 0 || j == COLUMN_NUM){
                canvas.drawLine(curX,startY,curX,endY,paintFactory.linePaint);
            }
            else{
                canvas.drawLine(curX,startY,curX,endY,paintFactory.linePaintDashed);
            }
            curX = curX + xSpacing;
        }
    }

    private void drawPrice(Canvas canvas){
        // 使用path方法绘制
        Path path = new Path();// 分时线的填充区域
        float xPixel = 0, yPixel = 0;
        FenShiPointModel pointModel;
        for (int i = 0; i < fenShiChartModel.getPoints().size(); i++) {
            pointModel = fenShiChartModel.getPoints().get(i);
            xPixel = pointModel.getPointX();
            yPixel = pointModel.getPointY();
            if (i == 0) {
                path.moveTo(xPixel, yPixel);
            }
            else {
                path.lineTo(xPixel, yPixel);
            }
        }
        canvas.drawPath(path, paintFactory.pricePaint);


    }

    private void drawAveragePrice(Canvas canvas){
        // 使用path方法绘制
        Path path = new Path();// 分时线的填充区域
        float xPixel = 0, yPixel = 0;
        FenShiPointModel pointModel;
        for (int i = 0; i < fenShiChartModel.getPoints().size(); i++) {
            pointModel = fenShiChartModel.getPoints().get(i);
            xPixel = pointModel.getPointX();
            yPixel = pointModel.getAverageY();
            if (i == 0) {
                path.moveTo(xPixel, yPixel);
            }
            else {
                path.lineTo(xPixel, yPixel);
            }
        }
        canvas.drawPath(path, paintFactory.averagePricePaint);
    }

    private void drawVolume(Canvas canvas){
        int highestVolume = fenShiChartModel.searchHightestVolume();
        if(highestVolume == 0){
            return;
        }
        float pixelsInOneVolume = (volumeEndY - volumeStartY)/highestVolume;
        for (int i = 0; i < fenShiChartModel.getPoints().size(); i++) {
            FenShiPointModel pointModel = fenShiChartModel.getPoints().get(i);
            float x = pointModel.getPointX();
            float y = volumeEndY - pixelsInOneVolume*pointModel.getVolume();
            if(i == 0){
                canvas.drawLine(x,volumeEndY,x,y,paintFactory.redTextPaint);
            }
            else{
                FenShiPointModel prePointModel = fenShiChartModel.getPoints().get(i-1);
                //如果比前一个价低就是绿色，不低就是红色
                if(pointModel.getLastPrice() < prePointModel.getLastPrice()){
                    canvas.drawLine(x,volumeEndY,x,y,paintFactory.greenTextPaint);
                }
                else{
                    canvas.drawLine(x,volumeEndY,x,y,paintFactory.redTextPaint);
                }
            }
        }
    }



    /**
     * 根据当前时间算出X坐标
     */
    private float getXPixelByTime(FenShiPointModel fenShiPointModel) {
        float ret = 0;
        float pixelsOneMinute = (fenShiEndX - fenShiStartX) / tradeTimeManager.getTotalMinutes();// 每1分钟时多少像素
        long currentTime = fenShiPointModel.getTimeLong();
        long startTime = tradeTimeManager.getStartTime();
        if (currentTime < startTime) {
            currentTime -= 1000 * 60 * 60 * 24;
        }
        long delta = currentTime - startTime;
        ret = fenShiStartX + pixelsOneMinute * (delta / 1000f / 60f);
        return ret;
    }

    /**
     * 根据当前价钱算出Y坐标(此方法耗时小于1ms)
     */
    private float getYPixelByPrice(float price, float closePrice) {

        float pixelsInPrice = 0 ;// 每1块钱是多少像素
        float highestPrice = fenShiChartModel.getHighestPrice();
        float lowestPrice = fenShiChartModel.getLowestPrice();
        if ((highestPrice - closePrice) > (closePrice - lowestPrice)) {
            pixelsInPrice = (fenShiEndY - fenShiStartY)/2/(highestPrice-closePrice);
        } else {
            pixelsInPrice = (fenShiEndY - fenShiStartY)/2/(closePrice-lowestPrice);
        }
        float ret = 0;
        //中间的昨收价格的y坐标
        float closeY = (fenShiEndY - fenShiStartY) / 2 + fenShiStartY;
        if (price > closePrice) {
            ret = closeY - (price - closePrice) * pixelsInPrice;
        } else {
            ret = closeY + (closePrice - price) * pixelsInPrice;
        }
        return ret;
    }


    //public methods

    public void calPointXY(){
        List<FenShiPointModel> fenShiPointModelList = fenShiChartModel.getPoints();
        for (int i = 0; i < fenShiPointModelList.size(); i++) {
            FenShiPointModel fenShiPointModel = fenShiPointModelList.get(i);
            float x = getXPixelByTime(fenShiPointModel);
            float y = getYPixelByPrice(fenShiPointModel.getLastPrice(),fenShiChartModel.getYesterdayClosePrice());
            float avgY = getYPixelByPrice(fenShiPointModel.getAveragePrice(),fenShiChartModel.getYesterdayClosePrice());
            fenShiPointModel.setPointX(x);
            fenShiPointModel.setPointY(y);
            fenShiPointModel.setAverageY(avgY);
        }
    }


    //getters and setters

    public void setTradeTimeManager(TradeTimeManager tradeTimeManager) {
        this.tradeTimeManager = tradeTimeManager;
    }

    public void setFenShiChartModel(FenShiChartModel fenShiChartModel) throws Exception {
            this.fenShiChartModel = (FenShiChartModel) fenShiChartModel.deepCopy();
    }
}
