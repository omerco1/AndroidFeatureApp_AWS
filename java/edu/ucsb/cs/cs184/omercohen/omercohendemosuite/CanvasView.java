package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by Donghao Ren on 10/18/2017.
 */

public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

    DrawingThread thd = new DrawingThread(this, 1);


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Add the class itself as the SurfaceView's SurfaceHolder's callback
        // This will allow the surfaceCreated, Changed, and Destroyed methods to be called
        getHolder().addCallback(this);
    }

    // The bitmap to store our painting
    Bitmap painting;
    float x;
    float y;
    float[][] balls = new float[10][10];
    float gravity = 0;
    // The canvas to draw to our painting
    Canvas paintingCanvas;


    int[] colors;


    Map<Integer,Integer> colorsMap = new HashMap<Integer,Integer>();

    // Keep track if the surface exists or not
    boolean surfaceExists = false;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);

        Log.d("post", "post seg");

        surfaceExists = true;
        //thd.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Create our bitmap
        //if (painting == null)
        paintingCanvas = new Canvas();


        if(painting != null) {

            Matrix matrix = new Matrix();
            matrix.postRotate(0);
            Bitmap omer = Bitmap.createBitmap(painting, 0, 0, painting.getWidth(), painting.getHeight(), matrix, true);

            paintingCanvas.setBitmap(painting);

        }
        else {

            painting = Bitmap.createBitmap(width*2, height*2, Bitmap.Config.ARGB_8888);
            Log.d("post", "post seg");

            paintingCanvas = new Canvas(painting);
            // Fill the canvas with white
            paintingCanvas.drawColor(Color.WHITE);
        }

        refreshView();
    }

    public void refreshView() {
        if(!surfaceExists) {
            Log.d("Note", "surface does not exist");
            return;
        }

        // Draw the painting bitmap to the SurfaceView
        Canvas surfaceCanvas = getHolder().lockCanvas();
        // surfaceCanvas is only valid between lockCanvas and unlockCanvasAndPost

        surfaceCanvas.drawBitmap(painting, 0, 0, null);
        getHolder().unlockCanvasAndPost(surfaceCanvas);
        // unlockCanvasAndPost will show the SurfaceView. invalidate() is not necessary here.
        //invalidate();
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceExists = false;
        //thd.stop();
    }

    int radius = 0;
    int i = 0;
    int r,g,b;
    int iter = 0;



    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (painting!=null) {
            gravity = gravity + 50;
            Paint paint = new Paint();


            //paint.setColor(Color.RED);

            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            Random rand = new Random();



            for (int z = 0; z< 10; z++ ) {
                if(iter < 4) {

                    int n = rand.nextInt(3);
                    if(n==1)
                        paint.setARGB(255, 255,200,0);
                    else if (n==2)
                        paint.setColor(Color.RED);
                    else
                        paint.setColor(Color.YELLOW);

                    int rx = rand.nextInt((int) gravity * 2) - (int) ((gravity - 50) * 2);
                    int ry = rand.nextInt((int) gravity * 2) - (int) ((gravity - 50) * 2);
                    balls[z][0] += rx;
                    balls[z][1] += ry;
                    paintingCanvas.drawCircle(balls[z][0], balls[z][1], 25, paint);
                    refreshView();
               } else {
//                    balls[z][1] += gravity;
//


                }
                //Thread.sleep(100) ;

            }
            clearscreen();

            iter++;
        }
    }

    public void setCoords() {
        if(iter==0) {
            balls[0][0] = x + 10;
            balls[0][1] = y - 10;
            balls[1][0] = x + 5;
            balls[1][1] = y - 5;
            balls[2][0] = x + 12;
            balls[2][1] = y + 12;
            balls[3][0] = x + 15;
            balls[3][1] = y;
            balls[4][0] = x;
            balls[4][1] = y - 15;
            balls[5][0] = x - 6;
            balls[5][1] = y - 12;
            balls[6][0] = x - 10;
            balls[6][1] = y - 10;
            balls[7][0] = x - 6;
            balls[7][1] = y;
            balls[8][0] = x + 10;
            balls[8][1] = y;
            balls[9][0] = x - 5;
            balls[9][1] = y + 10;


        }
         else if (iter ==1) {
             for (int z = 0; z < 10; z++) {
                 balls[z][0] *= 1.3;
                 balls[z][1] *= 1.3;

             }
         }

        else if (iter ==2) {
            for (int z = 0; z < 10; z++) {
                balls[z][0] *= 1.3;
                balls[z][1] *= 1.3;
            }
        }

        else {
            for (int z = 0; z< 10; z++) {
                balls[z][1] +=gravity;

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);


//        paint.setARGB(255, r, g, b);

        //Log.d("Im here", "inside the ontouchevent");
        int num_points = event.getPointerCount();

        Paint paint = new Paint();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clearscreen();
            thd.stop();
            gravity = 0;

            x = event.getX();
            y = event.getY();


            for(int z = 0; z< 10; z++) {

                balls[z][0] = x;
                balls[z][1] = y;

            }
            iter = 0;



            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paintingCanvas.drawCircle(event.getX(), event.getY(), 30, paint);

            refreshView();

            thd.start();

        }

        else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE) {

        }

        else if ((event.getAction() & MotionEvent.ACTION_MASK)== MotionEvent.ACTION_UP) {


        }
        else if ((event.getActionMasked()) == MotionEvent.ACTION_POINTER_DOWN) {



        }
        else if  ((event.getActionMasked()  & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP) {


        }
        return true;
    }

    public void clearscreen() {

        paintingCanvas.drawColor(Color.WHITE);
        //canv.painting = null;
        Log.d("clearing", "currently clearing the screen in this method");
        refreshView();

    }

    public void getColorIndex(int[] colors) {
        if (i == colors.length - 1)
            i = 0;
        else
            i++;

    }
}