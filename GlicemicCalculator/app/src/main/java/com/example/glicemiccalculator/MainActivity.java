package com.example.glicemiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView ageTextView;
    private TextView responseTextView;
    private SeekBar ageSeekBar;
    private RadioButton fastingRadioButton;
    private RadioButton notFastingRadioButton;
    private Button consultButton;
    private EditText valueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageTextView.setText("Your age: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        consultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    public void initializeViews() {
        ageTextView = findViewById(R.id.tvAge);
        responseTextView = findViewById(R.id.tvReponse);
        ageSeekBar = findViewById(R.id.sbAge);
        consultButton = findViewById(R.id.btnConsulter);
        fastingRadioButton = findViewById(R.id.rbtOui);
        notFastingRadioButton = findViewById(R.id.rbtNon);
        valueEditText = findViewById(R.id.etValeur);
    }

    public void calculate() {
        int age = ageSeekBar.getProgress();
        boolean isFasting = fastingRadioButton.isChecked();
        double measuredValue = 0.0;

        String measuredValueString = valueEditText.getText().toString();
        if (!measuredValueString.isEmpty()) {
            measuredValue = Double.parseDouble(measuredValueString);
        }

        // Perform the blood glucose level calculation based on the provided input
        String result = calculateBloodGlucose(age, isFasting, measuredValue);

        // Update the TextView with the blood glucose level result
        responseTextView.setText(result);
    }

    private String calculateBloodGlucose(int age, boolean isFasting, double measuredValue) {
        String result;
        double mmolL = measuredValue / 18.0;  // Convert mg/dL to mmol/L

        if (mmolL < 3.9) {
            result = "Low blood glucose level. Consult a doctor if you experience symptoms.";
        } else if (mmolL >= 3.9 && mmolL <= 6.0) {
            result = "Normal blood glucose level.";
        } else if (mmolL > 6.0 && mmolL <= 7.0) {
            result = "High blood glucose level. Consult a doctor to discuss this result.";
        } else {
            result = "Very high blood glucose level. Consult a doctor immediately.";
        }

        // Emphasize consulting a doctor
        result += "\nRemember, this app is for informational purposes only. Always consult a doctor for any concerns about your blood sugar levels.";

        return result;
    }
}
