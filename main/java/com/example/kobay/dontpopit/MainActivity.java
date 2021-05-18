package com.example.kobay.dontpopit;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;


public class MainActivity extends Activity {

    MyCanvas myCanvas;

    double increaseRadius = 3;

    Handler increase = new Handler();

    //this updates the textbox
    Runnable increaseRunnable = new Runnable() {
        @Override
        public void run() {

            increaseRadius = (3*(threshold - counter)/(threshold*1.0))+.90;
            //System.out.println(a + "");
            myCanvas.addRadius(increaseRadius);
            counter++;
            myCanvas.addScore(1);
            increase.postDelayed(this,10);
            //myCanvas.setBalloonPopped(false);

            checkPopped();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myCanvas = new MyCanvas(this);
        setContentView(myCanvas);

    }
    ;



    int counter = 1;
    int threshold = randomNumber();

    public int randomNumber(){

        return (int)(Math.random()*50)+100;

    }

    public void checkPopped(){
        if(counter == threshold){
            //balloonPopped = true;
            //startTimer();
            counter = 0;
            x = 0;
            increase.removeCallbacks(increaseRunnable);
            myCanvas.resetScore();
            myCanvas.resetRadius();
           // myCanvas.setBalloonPopped(true);
            threshold = randomNumber();
            differenceThreshold = 50;

            tappedOnce = false;
            Toast.makeText(this,"Game Over! Balloon Popped!",Toast.LENGTH_LONG).show();
        }
    }

    public void checkBalloonSize(){
        if(threshold - counter > differenceThreshold){
            counter = 0;
            myCanvas.resetScore();
            myCanvas.resetRadius();
            threshold = randomNumber();
            differenceThreshold = 50;
            tappedOnce = false;
            x = 0;
            Toast.makeText(this,"Game Over! Balloon's Too Small!",Toast.LENGTH_LONG).show();
        }
        else{
            differenceThreshold = (int)(differenceThreshold*.8);
            tappedOnce = false;
            counter = 0;
            myCanvas.resetRadius();
            threshold = randomNumber();
            x = 0;
            Toast.makeText(this,"Next Round!",Toast.LENGTH_LONG).show();

        }
    }

    int x = 0;
    int differenceThreshold = 50;
    boolean tappedOnce = false;
    boolean balloonPopped = false;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(x==0 && !tappedOnce){
                increaseRunnable.run();
                x = 1;
                tappedOnce = true;
            }
            else if(x==1 && tappedOnce){
                increase.removeCallbacks(increaseRunnable);
                checkBalloonSize();
                x = 0;
            }

            //Toast.makeText(this,threshold + "",Toast.LENGTH_LONG).show();
        }


        return false;
    }

    CountDownTimer cTimer = null;

    //start timer function
    void startTimer() {
        cTimer = new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                myCanvas.setTime(millisUntilFinished);
            }
            public void onFinish() {
                myCanvas.setBalloonPopped(true);
                //myCanvas.resetHexValue();
                balloonPopped = false;
                cancelTimer();
            }
        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            //myCanvas.setBalloonPopped(false);
            cTimer.cancel();
    }

}
