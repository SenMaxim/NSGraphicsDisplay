package com.example.swtor.nsgraphicsdisplay;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by swtor on 2/17/2018.
 */

public class ControlFragment extends Fragment {
    private OnPlaySelectedListener hostListener;

    private double[] genotypeFrequencies = new double[3];
    private double[] absoluteFitness = new double[3];

    // Widgets of this fragment
    private EditText genotypeAA;
    private EditText genotypeAa;
    private EditText genotypeaa;
    private EditText absoluteFitnessAA;
    private EditText absoluteFitnessAa;
    private EditText absoluteFitnessaa;

    private TextView currentFrequencies;

    public interface OnPlaySelectedListener {
        public void onImageButtonClicked(double[] gf, double[] af, TextView test);
    }

    // Store the host activity (which is a listener) that will fire events once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlaySelectedListener) {
            hostListener = (OnPlaySelectedListener) context;
        }
        else {
            throw new ClassCastException(context.toString() + " must implement ControlFragment.OnPlayListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        return inflater.inflate(R.layout.control_fragment, container, false);
    }

    // This event is triggered after onCreateView if the View returned is non-null
    // View lookups/setups occur here
    @Override
    public void onViewCreated(View view, Bundle savedInstances) {

        // Initialize widgets
        genotypeAA = (EditText)view.findViewById(R.id.genotype_AA);
        genotypeAa = (EditText)view.findViewById(R.id.genotype_Aa);
        genotypeaa = (EditText)view.findViewById(R.id.genotype_aa);
        absoluteFitnessAA = (EditText)view.findViewById(R.id.absolute_fitness_AA);
        absoluteFitnessAa = (EditText)view.findViewById(R.id.absolute_fitness_Aa);
        absoluteFitnessaa = (EditText)view.findViewById(R.id.absolute_fitness_aa);

        currentFrequencies = (TextView)view.findViewById(R.id.current_frequencies);

        // Set a listener for the ImageButton
        PlayButtonImage playButton = view.findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genotypeFrequencies[0] = Double.parseDouble(genotypeAA.getText().toString());
                genotypeFrequencies[1] = Double.parseDouble(genotypeAa.getText().toString());
                genotypeFrequencies[2] = Double.parseDouble(genotypeaa.getText().toString());

                absoluteFitness[0] = Double.parseDouble(absoluteFitnessAA.getText().toString());
                absoluteFitness[1] = Double.parseDouble(absoluteFitnessAa.getText().toString());
                absoluteFitness[2] = Double.parseDouble(absoluteFitnessaa.getText().toString());

                hostListener.onImageButtonClicked(genotypeFrequencies, absoluteFitness, currentFrequencies);
            }
        });
    }
}
