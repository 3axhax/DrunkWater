package com.example.drunkwater;

import java.text.DecimalFormat;

public class DrunkWater {

    float total_value = 0;

    public void changeValue(String sign, String v) {
        if (!v.isEmpty()) {
            float value = Float.parseFloat(v);
            switch (sign) {
                case "+":
                    total_value += value;
                    break;
                case "-":
                    total_value -= value;
                    break;
            }
            if (total_value < 0) total_value = 0;
        }
    }

    public String getTotalValue() {
        return Float.toString(total_value);
    }

    public void resetValue() {
        total_value = 0;
    }

    public void setValue(float total) {
        total_value = total;
    }
}
