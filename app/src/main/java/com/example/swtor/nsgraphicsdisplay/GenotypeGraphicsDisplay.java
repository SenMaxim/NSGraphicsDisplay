package com.example.swtor.nsgraphicsdisplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by swtor on 2/21/2018.
 */

public class GenotypeGraphicsDisplay extends View {
    private static final String TAG = "GenotypeGraphicsDisplay";

    private int[] genotypeNumbers = new int[3];
    private Rect block;
    private Paint[] paint = new Paint[3];
    //private Paint outlinePaint;
    private int blockWidth;
    private int blockHeight;

    boolean numbersSet = false;

    public GenotypeGraphicsDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize Paint with colors
        paint[0] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint[0].setColor(Color.rgb(0, 131, 143));

        paint[1] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint[1].setColor(Color.rgb(77, 208, 225));

        paint[2] = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint[2].setColor(Color.rgb(224, 247, 250));

        // Initialize Rect
        block = new Rect(0, 0, 0, 0);
    }

    @Override
    public void onDraw(Canvas canvas) {

        blockWidth = canvas.getWidth() / 10;
        blockHeight = canvas.getHeight() / 10;
        //canvas.drawText("blockSize is " + blockSize, 0, 0, paint[]);
        //block.set(0, 0, canvas.getWidth(), canvas.getHeight());
        //canvas.drawRect(block, paint[1]);
        //block.set(0 * blockSize, 0 * blockSize, 0 * blockSize + blockSize, 0 * blockSize + blockSize);
        //canvas.drawRect(block, paint[2]);

        if (numbersSet) {
            // Draw
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    // Pick genotype and check if available

                    int rand = (int) (Math.random() * 3);

                    while (genotypeNumbers[rand] < 0) {
                        //Log.d("here", "rand is " + rand + " \n" + " genotypeNumbers[rand] is " + genotypeNumbers[rand]);
                        Log.d(TAG, "rand is " + rand + " \n" + " genotypeNumbers[rand] is " + genotypeNumbers[rand]);
                        rand = (int) (Math.random() * 3);
                    }

                    Log.d(TAG, "rand is " + rand + " \n" + " genotypeNumbers[rand] is " + genotypeNumbers[rand]);
                    block.set(i * blockWidth, j * blockHeight, i * blockWidth + blockWidth, j * blockHeight + blockHeight);
                    canvas.drawRect(block, paint[rand]);
                    genotypeNumbers[rand]--;
                }
            }
        }
        numbersSet = false;

    }

    public void setGenotypeNumbers(double[] frequencies) {
        // Extract Numbers from given frequencies
        genotypeNumbers[0] = (int)(frequencies[0] * 100);
        genotypeNumbers[1] = (int)(frequencies[1] * 100);
        genotypeNumbers[2] = 100 - genotypeNumbers[1] - genotypeNumbers[0];

        // Draw
        numbersSet = true;
        invalidate();
    }

    public void test() {
        // Set random values for genotypes
        double[] genotypeNumbersTest = new double[3];

        genotypeNumbersTest[0] = .10;
        genotypeNumbersTest[1] = .10;
        genotypeNumbersTest[2] = .80;

        setGenotypeNumbers(genotypeNumbersTest);
    }

}
