package com.example.drunkwater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DrunkWater drunkWater = new DrunkWater();
    Button b_minus, b_plus, b_reset;
    EditText edit_value;
    TextView text_value;

    SharedPreferences my_preference;
    SharedPreferences.Editor editor;

    String total_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_preference = PreferenceManager.getDefaultSharedPreferences(this);
        editor = my_preference.edit();

        b_minus = new Button(this);
        b_plus = new Button(this);
        b_reset = new Button(this);
        edit_value = new EditText(this);
        text_value = new TextView(this);

        b_minus = (Button) findViewById(R.id.minus_button);
        b_plus = (Button) findViewById(R.id.plus_button);
        b_reset = (Button) findViewById(R.id.reset_button);
        edit_value = (EditText) findViewById(R.id.editValue);
        text_value = (TextView) findViewById(R.id.textValue);

        float total = my_preference.getFloat("Total", 0);
        drunkWater.setValue(total);
        setValueText();

        View.OnClickListener btnClick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String sign = b.getText().toString();
                String value = edit_value.getText().toString();
                drunkWater.changeValue(sign, value);
                editor.putFloat("Total", Float.parseFloat(drunkWater.getTotalValue()));
                editor.apply();
                setValueText();
            }
        };

        View.OnClickListener resetBtnClick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drunkWater.resetValue();
                editor.putFloat("Total", Float.parseFloat(drunkWater.getTotalValue()));
                editor.apply();
                setValueText();
            }
        };

        b_minus.setOnClickListener(btnClick);
        b_plus.setOnClickListener(btnClick);
        b_reset.setOnClickListener(resetBtnClick);

    }

    void setValueText() {
        double total_value = Math.round(Double.parseDouble(drunkWater.getTotalValue()) * 100.0) / 100.0;
        total_text = Double.toString(total_value) + " L";
        text_value.setText(total_text);
    }


}