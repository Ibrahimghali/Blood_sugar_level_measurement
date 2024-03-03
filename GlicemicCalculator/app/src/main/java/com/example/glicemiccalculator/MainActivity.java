package com.example.glicemiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge;
    private TextView tvReponse;
    private SeekBar sbAge;
    private RadioButton rbIsFasting;
    private RadioButton rbIsNotFasting;
    private Button btnConsulter;
    private EditText etValeur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAge.setText("Votre âge: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer();
            }
        });
    }

    public void init() {
        tvAge = findViewById(R.id.tvAge);
        tvReponse = findViewById(R.id.tvReponse);
        sbAge = findViewById(R.id.sbAge);
        btnConsulter = findViewById(R.id.btnConsulter);
        rbIsFasting = findViewById(R.id.rbtOui);
        rbIsNotFasting = findViewById(R.id.rbtNon);
        etValeur = findViewById(R.id.etValeur);
    }

    public void calculer() {
        int age = sbAge.getProgress();
        boolean isFasting = rbIsFasting.isChecked();
        double measuredValue = 0.0;

        String measuredValueString = etValeur.getText().toString();
        if (!measuredValueString.isEmpty()) {
            measuredValue = Double.parseDouble(measuredValueString);
        }

        // Perform the blood sugar level calculation based on the provided input
        double calculatedValue = calculateBloodSugar(age, isFasting, measuredValue);

        // Update the result text view with the calculated value
        tvReponse.setText(String.valueOf(calculatedValue));
    }

    private double calculateBloodSugar(int age, boolean isFasting, double measuredValue) {
        // Replace this with your actual blood sugar level calculation logic
        // This is a dummy calculation just for demonstration purposes
        double calculatedValue = measuredValue * (isFasting ? 2.0 : 1.0) + age * 0.5;
        return calculatedValue;
    }
}