package com.android.jsanchez.seccion_01_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private SeekBar seekBarAge;
    private TextView textViewAge;
    private Button btnNext;
    private RadioButton radioButtonGreeter;
    private RadioButton radioButtonFarewell;

    private String name = "";
    private int age = 18;
    private final int MAX_AGE = 60;
    private final int MIN_AGE = 16;

    public static final int GREETER_OPTION = 1;
    public static final int FAREWELL_OPTION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            name = bundle.getString("name");
        }

        seekBarAge = findViewById(R.id.seekBarAge);
        textViewAge = findViewById(R.id.textViewCurrentAge);
        btnNext = findViewById(R.id.buttonToThirdActivity);
        radioButtonGreeter = findViewById(R.id.radioButtonGreeter);
        radioButtonFarewell = findViewById(R.id.radioButtonFarewell);

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean b) {
                age = currentAge;
                textViewAge.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                age = seekBar.getProgress();
                textViewAge.setText(age + "");
                if (age > MAX_AGE) {
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The max age allowed is: "+MAX_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else if (age < MIN_AGE) {
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The min age allowed is: "+MIN_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                }
            }
        });
        
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                // Si el botón de greeter esta activo, option valdrá 1, si no, 2
                int option = (radioButtonGreeter.isChecked()) ? GREETER_OPTION : FAREWELL_OPTION;
                intent.putExtra("option", option);
                startActivity(intent);
                Toast.makeText(SecondActivity.this, seekBarAge.getProgress()+"", Toast.LENGTH_LONG).show();
            }
        });
    }
}
