package com.example.swtor.nsgraphicsdisplay;

import android.animation.ValueAnimator;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ControlFragment.OnPlaySelectedListener {
    private GenotypeGraphicsDisplay display;

    private TextView currentFrequencies;
    private double[] genotypeFrequencies;
    private double[] absoluteFitness;

    Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageButtonClicked(double[] gf, double[] af, TextView test) {
        // Initialize fields
        display = (GenotypeGraphicsDisplay)findViewById(R.id.display);

        genotypeFrequencies = gf;
        absoluteFitness = af;
        currentFrequencies = test;
    }

    private class GenotypeTimerTask extends TimerTask {
        private GenotypeGraphicsDisplay display;
        private TextView currentFrequencies;
        private double[] genotypeFrequencies;
        private double[] absoluteFitness;
        private int generation = 1;

        public GenotypeTimerTask(GenotypeGraphicsDisplay display, TextView text, double[] genotypeFrequencies, double[] absoluteFitness) {
            currentFrequencies = text;
            this.genotypeFrequencies = genotypeFrequencies;
            this.absoluteFitness = absoluteFitness;

            this.display = display;
        }

        private void updateGenotypeFrequencies() {
            double meanFitness =
                    genotypeFrequencies[0] * absoluteFitness[0]
                            + genotypeFrequencies[1] * absoluteFitness[1]
                            + genotypeFrequencies[2] * absoluteFitness[2];

            // Starting allele frequencies
            double p0 = genotypeFrequencies[0] + genotypeFrequencies[1] / 2;
            double q0 = genotypeFrequencies[2] + genotypeFrequencies[1] / 2;

            double afterAA = genotypeFrequencies[0] * (absoluteFitness[0] / meanFitness);
            double afterAa = genotypeFrequencies[1] * (absoluteFitness[1] / meanFitness);
            double afteraa = genotypeFrequencies[2] * (absoluteFitness[2] / meanFitness);

            double p1 = afterAA + afterAa / 2;
            double q1 = afteraa + afterAa / 2;

            // Update genotype Frequencies
            genotypeFrequencies[0] = p1 * p1;
            genotypeFrequencies[1] = 2 * p1 * q1;
            genotypeFrequencies[2] = q1 * q1;


        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (generation <= 100) {
                        // Update fields
                        updateGenotypeFrequencies();

                        String frequenciesText = "f" + generation + "(AA) = " + (int)(genotypeFrequencies[0] * 1000) / 1000.0
                                + " f" + generation + "(Aa) = " + (int)(genotypeFrequencies[1] * 1000) / 1000.0 + " f" + generation + "(aa) = " + (int)(genotypeFrequencies[2] * 1000) / 1000.0;
                        currentFrequencies.setText(frequenciesText);

                        // Update display
                        display.setGenotypeNumbers(genotypeFrequencies);

                        generation++;
                    }
                    else {
                        cancel();
                    }
                }
            });
        }
    }
}
