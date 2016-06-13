package com.puxtech.ybk.hangqing.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.puxtech.ybk.hangqing.util.PriceUtil;

import java.util.List;

/**
 * Created by fanshuo on 16/4/28.
 */
public class FenShiView extends View {

    public static final int FENSHI_ROW_NUM = 4;//行数
    public static final int VOLUME_ROW_NUM = 2;//行数
    public static final int COLUMN_NUM = 4;//列数
    public static final int LEFT_TEXT_WIDTH_DP = 40;
    public static final int TOP_TEXT_HEIGHT_DP = 20;
    public static final int MIDDLE_TEXT_HEIGHT_DP = 20;
    public static final int BOTTOM_TEXT_HEIGHT_DP = 30;
    private Context context;
    private FenShiPaintFactory paintFactory;//画笔工厂
    private float fenShiStartY,fenShiEndY,fenShiStartX,fenShiEndX;//分时区域的起止点坐标
    private float volumeStartY,volumeEndY,volumeStartX,volumeEndX;//交易量区域的起止点坐标
    private float rowHeight;//一行所占高度
    private TradeTimeManager tradeTimeManager;
    private FenShiChartModel fenShiChartModel;
    private int mSlop;// 视为滑动的临界点
    private float onTouchStartX = 0, onTouchStartY, onTouchEndX, onTouchEndY;

    //十字线
    private FenShiPointModel crossLinePoint;//要画的那个分时点
    private OnShowListener onShowListener;
    private boolean drawCrossLine;//是否绘制十字线

    public FenShiView(Context context) {
        super(context);
        init(context);
    }

    public FenShiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FenShiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化本类需要的信息
     */
    private void init(Context mContext){
        this.context = mContext;
        paintFactory = new FenShiPaintFactory(context);
        ViewConfiguration vc = ViewConfiguration.get(context);
        mSlop = vc.getScaledTouchSlop();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(tradeTimeManager == null){
            tradeTimeManager = new TradeTimeManager(1462237200000l,1462276800000l);
        }
        initData();
        drawFrame(canvas);
        drawBottomText(canvas);
        if(fenShiChartModel != null){
            drawLeftText(canvas);
            drawPrice(canvas);
            drawAveragePrice(canvas);
            drawVolume(canvas);
            if(drawCrossLine){
                drawCrossLine(canvas);
                drawCrossLineText(canvas);
            }
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                onTouchStartX = event.getX();
                onTouchStartY = event.getY();
                onTouchEndX = event.getX();
                onTouchEndY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                onTouchEndX = event.getX();
                onTouchEndY = event.getY();
                if (drawCrossLine) {
                    crossLinePoint = searchPointByX(onTouchEndX);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                onTouchEndX = event.getX();
                float offset2 = onTouchEndX - onTouchStartX;
                // 如果点击的是上面的部分
                if (Math.abs(offset2) < mSlop) {
                    // 如果有分时数据，就开始画十字线
                    if (fenShiChartModel != null && fenShiChartModel.getPoints() != null && fenShiChartModel.getPoints().size() > 0) {
                        // 把curPointList传给画十字线的view，让它画十字线
//                        crossLineView.setFenShiChartModel(fenShiChartModel);
//                        crossLineView.startDrawCrossLine(event.getX());
//                        crossLineView.setCanTouch(true);

                        if(drawCrossLine){
                            drawCrossLine = false;
                            invalidate();
                            onShowListener.onHide();
                        }
                        else{
                            drawCrossLine = true;
                            crossLinePoint = searchPointByX(event.getX());
                            invalidate();
                            onShowListener.onShow();
                        }

                    }
                }
                break;
        }
        return true;
    }

    /**
     * 查找最近的点
     */
    private FenShiPointModel searchPointByX(float x) {
        if(fenShiChartModel == null){
            return null;
        }
        List<FenShiPointModel> allPoint = fenShiChartModel.getPoints();
        if (allPoint == null || allPoint.size() == 0) {
            return null;
        }
        FenShiPointModel point = null;
        float deltaX = 10000;
        for (int i = 0; i < allPoint.size(); i++) {
            FenShiPointModel item = allPoint.get(i);
            if (Math.abs(item.getPointX() - x) < deltaX) {
                deltaX = Math.abs(item.getPointX() - x);
                point = item;
            }
        }
        return point;
    }

    private void initData() {
        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();
        //一行的高度，(总高度-顶部文字高度-中间文字高度-底部文字高度)/行数
        rowHeight = (viewHeight - ChartsScreenSizeUtil.Dp2Px(context,TOP_TEXT_HEIGHT_DP) - ChartsScreenSizeUtil.Dp2Px(context,MIDDLE_TEXT_HEIGHT_DP) - ChartsScreenSizeUtil.Dp2Px(context,BOTTOM_TEXT_HEIGHT_DP))/(float)(FENSHI_ROW_NUM+VOLUME_ROW_NUM);
        fenShiStartX = ChartsScreenSizeUtil.Dp2Px(context,LEFT_TEXT_WIDTH_DP);
        fenShiEndX = viewWidth;
        fenShiStartY = ChartsScreenSizeUtil.Dp2Px(context,TOP_TEXT_HEIGHT_DP);
        fenShiEndY = fenShiStartY + rowHeight * (float)FENSHI_ROW_NUM;

        volumeStartX = ChartsScreenSizeUtil.Dp2Px(context,LEFT_TEXT_WIDTH_DP);
        volumeEndX = viewWidth;
        volumeStartY = fenShiEndY + ChartsScreenSizeUtil.Dp2Px(context,MIDDLE_TEXT_HEIGHT_DP);
        volumeEndY = viewHeight-ChartsScreenSizeUtil.Dp2Px(context,BOTTOM_TEXT_HEIGHT_DP);
    }

    private void drawFrame(Canvas canvas){
        drawBoarder(canvas,FENSHI_ROW_NUM,COLUMN_NUM,fenShiStartX,fenShiStartY,fenShiEndX,fenShiEndY);
        drawBoarder(canvas, 2, COLUMN_NUM, volumeStartX, volumeStartY, volumeEndX, volumeEndY);
    }

    private void drawBoarder(Canvas canvas, int row,int column,float startX,float startY,float endX,float endY){
        float curY = startY;// 画横线时，当前这条线的y坐标
        // 画横线
        for (int i = 0; i < (row + 1); i++) {
            if (i == 0 || i == row) {
                canvas.drawLine(startX, curY, endX, curY, paintFactory.linePaint);
            } else {
                canvas.drawLine(startX, curY, endX, curY, paintFactory.linePaintDashed);
            }
            curY = curY + rowHeight;
        }

        //画竖线
        float xSpacing = (endX - startX)/column;//列间隔
        float curX = startX;
        for (int j = 0; j < (column + 1); j++) {
            if(j == 0){
                canvas.drawLine(curX,startY,curX,endY,paintFactory.linePaint);
            }
            else if(j == column){
                canvas.drawLine(curX,startY,curX,endY,paintFactory.linePaint);
            }
            else{
                canvas.drawLine(curX,startY,curX,endY,paintFactory.linePaintDashed);
            }
            curX = curX + xSpacing;
        }
    }

    private void drawTopText(Canvas canvas){
        String text = "现价：--        均价：0.00";
        canvas.drawText(text, fenShiStartX, fenShiStartY-4, paintFactory.whiteTextPaintRight);
    }

    private void drawLeftText(Canvas canvas){
        //字体的高度
        float fontHeight = paintFactory.leftTextSize;
        float centerY = fenShiStartY + FENSHI_ROW_NUM/2*rowHeight + fontHeight/2;
        String centerPrice = PriceUtil.keepPrecision(fenShiChartModel.getYesterdayClosePrice(), fenShiChartModel.getMcu());
        canvas.drawText(centerPrice,fenShiStartX,centerY,paintFactory.leftTextPaintWhite);

        float highestPrice = fenShiChartModel.getHighestPrice();
        float lowestPrice = fenShiChartModel.getLowestPrice();
        float closePrice = fenShiChartModel.getYesterdayClosePrice();
        float rowPrice;
        if(Math.abs(highestPrice-closePrice) > Math.abs(lowestPrice-closePrice)){
            rowPrice = Math.abs(highestPrice-closePrice)/(FENSHI_ROW_NUM/2);
        }
        else{
            rowPrice = Math.abs(lowestPrice-closePrice)/(FENSHI_ROW_NUM/2);
        }

        for (int i = 1; i <= (FENSHI_ROW_NUM / 2); i++) {
            //画上半部分的文字
            float curTopY = centerY - rowHeight*i;
            float curTopPrice = closePrice + rowPrice*i;
            String curTopPriceString = PriceUtil.keepPrecision(curTopPrice,fenShiChartModel.getMcu());
            canvas.drawText(curTopPriceString,fenShiStartX,curTopY,paintFactory.leftTextPaintRed);

            //画下半部分的文字
            float curBottomY = centerY + rowHeight*i;
            float curBottomPrice = closePrice - rowPrice*i;
            String curBottomPriceString = PriceUtil.keepPrecision(curBottomPrice,fenShiChartModel.getMcu());
            canvas.drawText(curBottomPriceString,fenShiStartX,curBottomY,paintFactory.leftTextPaintGreen);
        }

        //画交易量左侧文字
        int highestVolume = fenShiChartModel.searchHightestVolume();
        String topVolumeStr = highestVolume+"";
        String centerVolumeStr = highestVolume/2+"";
        float topVolumeY = volumeStartY;
        float centerVolumeY = volumeStartY + rowHeight;
        canvas.drawText(topVolumeStr,volumeStartX,topVolumeY+fontHeight,paintFactory.leftTextPaintYellow);
        canvas.drawText(centerVolumeStr,volumeStartX,centerVolumeY,paintFactory.leftTextPaintYellow);
    }

    private void drawRightText(Canvas canvas){

    }

    private void drawBottomText(Canvas canvas){
        float leftX = volumeStartX;
        float rightX = volumeEndX;

        float oneMinX = (rightX - leftX) / tradeTimeManager.getTotalMinutes();
        String[] bottomTextArray = tradeTimeManager.getBottomTextArray();
        int[] bottomTextOffsetMinute = tradeTimeManager.getBottomOffsetMinuteArray();
        String curText = "";
        for (int i = 0; i < bottomTextArray.length; i++) {
            int minutes = bottomTextOffsetMinute[i];
            float x = leftX + minutes * oneMinX;
            float y = volumeEndY;

            curText = bottomTextArray[i];

            if(i == bottomTextArray.length-1){
                Paint.FontMetrics fontMetrics = paintFactory.whiteTextPaint.getFontMetrics();
                //字体的高度
                float fontHeight = fontMetrics.descent-fontMetrics.ascent;
                //下移字体高度的距离，用来实现字体在基准线之下的效果
                canvas.drawText(curText, x, y + fontHeight, paintFactory.whiteTextPaint);
            }
            else{
                Paint.FontMetrics fontMetrics = paintFactory.whiteTextPaint.getFontMetrics();
                //字体的高度
                float fontHeight = fontMetrics.descent-fontMetrics.ascent;
                //下移字体高度的距离，用来实现字体在基准线之下的效果
                canvas.drawText(curText, x, y + fontHeight, paintFactory.whiteTextPaintRight);
            }
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
                canvas.drawLine(x, volumeEndY, x, y, paintFactory.redTextPaint);
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

    private void drawCrossLine(Canvas canvas) {
        if(crossLinePoint == null){
            return;
        }
        float screenWidth = getMeasuredWidth();
        float screenHeight = getMeasuredHeight();
        float x = crossLinePoint.getPointX();
        float y = crossLinePoint.getPointY();
        canvas.drawLine(0, y, screenWidth, y, paintFactory.crossLinePaint);
        canvas.drawLine(x, 0, x, screenHeight, paintFactory.crossLinePaint);
    }

    private void drawCrossLineText(Canvas canvas) {
        String lastPrice = PriceUtil.keepPrecision(crossLinePoint.getLastPrice(), fenShiChartModel.getMcu());
        String avgPrice = PriceUtil.keepPrecision(crossLinePoint.getAveragePrice(), fenShiChartModel.getMcu());
        String time = crossLinePoint.getTimeStr();
        String text = "时间："+time+"    现价："+lastPrice+"    均价："+avgPrice;
        canvas.drawText(text, fenShiStartX, fenShiStartY - 4, paintFactory.topTextPaint);

        String volumeText = "成交量："+crossLinePoint.getVolume();
        canvas.drawText(volumeText, volumeStartX, volumeStartY - 4, paintFactory.volumeTextPaint);
    }

    /**
     * 根据当前时间算出X坐标（此方法耗时1ms-2ms）
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
        initData();
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

    public void addNewPoints(List<FenShiPointModel> points){
        fenShiChartModel.getPoints().addAll(points);
    }


    //getters and setters

    public void setTradeTimeManager(TradeTimeManager tradeTimeManager) {
        this.tradeTimeManager = tradeTimeManager;
    }

    public void setFenShiChartModel(FenShiChartModel fenShiChartModel) throws Exception {
        this.fenShiChartModel = (FenShiChartModel) fenShiChartModel.deepCopy();
    }

    public void setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }
    //public classes

    public interface OnShowListener{
        public void onShow();
        public void onHide();
    }
}
