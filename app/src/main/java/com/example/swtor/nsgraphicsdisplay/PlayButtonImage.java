package com.example.swtor.nsgraphicsdisplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by swtor on 2/18/2018.
 */

public class PlayButtonImage extends View {
    private float[] points = new float[12];
    private Paint paint;
    private Paint paintText;

    public PlayButtonImage(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(60);
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        float triangleWidth = canvasWidth * 0.6f;
        float triangleHeight = canvasHeight * 0.8f;

        // Up - Down line
        points[0] = canvasWidth / 2 - triangleWidth / 2;    // x0
        points[1] = canvasHeight / 2 - triangleHeight / 2;  // y0
        points[2] = points[0];                              // x1
        points[3] = canvasHeight / 2 + triangleHeight / 2;  // y1

        // Up - RightDown line
        points[4] = canvasWidth / 2 - triangleWidth / 2;    // x2
        points[5] = points[1];                              // y2
        points[6] = canvasWidth / 2 + triangleWidth / 2;    // x3
        points[7] = canvasHeight / 2;                        // y3

        // Up - RightUp line
        points[8] = points[4];                              // x4
        points[9] = points[3];                              // y4
        points[10] = canvasWidth / 2 + triangleWidth / 2;   // x5
        points[11] = canvasHeight / 2;                       // y5

        canvas.drawLines(points, paint);

        // Draw Text
        //canvas.drawText("Animate!", canvasWidth / 2, canvasHeight / 2, paintText);
    }
}
