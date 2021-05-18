package com.example.kobay.dontpopit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCanvas extends View {

    public MyCanvas(Context context){
        super(context);
    }

    int radius = 150;
    long time;
    int score = 0;
    String greenHexValue = "00";
    String blueHexValue = "00";
    String hexValue = "f82838";
    int decValue;
    Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.balloons);
    Bitmap pop = BitmapFactory.decodeResource(getResources(), R.drawable.pop);
    boolean balloonPopped = false;

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        Paint paint = new Paint();
        paint.setAlpha(60);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setColor(Color.parseColor("#" + hexValue));

        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha(60);

        canvas.drawBitmap(background, 0, 0, alphaPaint);
        canvas.drawCircle(x/2, y/2, radius, paint);

        if(balloonPopped){
            Paint paint3 = new Paint();
            paint3.setStyle(Paint.Style.FILL);
            paint3.setColor(Color.WHITE);
            canvas.drawPaint(paint3);
            paint3.setColor(Color.parseColor("#" + hexValue));
            canvas.drawBitmap(pop, x/2 - 350, y/2 - 250, paint3);
//            balloonPopped = false;
//            try { Thread.sleep(1000); }
//            catch (InterruptedException ex) { android.util.Log.d("YourApplicationName", ex.toString()); }

        }
        else{
            //canvas.drawBitmap(null, x/2 - 350, y/2 - 250, paint);
        }

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.BLACK);
        paint2.setTextSize(80);
        canvas.drawText("Score:" + score, 50, 105, paint2);

        postInvalidateDelayed(500);
        invalidate();



    }

    protected void onDraw(Canvas canvas,boolean popped){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAlpha(100);
        canvas.drawBitmap(background, 0, 0, paint);
    }

    public void deletePop(){

    }

    int x = 0;

    public void changeColor(){

        decValue = Integer.parseInt(hexValue,16);
        if(x == 0){
            decValue+=1048833;
            x = 1;
        }
        else if(x == 1){
            decValue+=1114114;
            x = 0;
        }
       // decValue+=1606.250;
        hexValue = Integer.toHexString(decValue);

    }

    public void resetHexValue(){
        greenHexValue = "00";
        blueHexValue = "00";
    }

    public void setBalloonPopped(boolean x){
        balloonPopped = x;

    }

    public void addScore(int s){
        score+=s;
    }

    public void resetScore(){
        score = 0;
    }

    public void setTime(long t){
        time = t;
    }

    public void addRadius(double r){
        radius+=r;
    }

    public void resetRadius(){
        radius = 150;
    }
}
