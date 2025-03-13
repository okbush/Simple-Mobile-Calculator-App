package com.example.simple_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView resultText;
    private double num1, num2;
    private boolean isAddition, isSubtraction, isMultiplication, isDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText2);
        resultText = findViewById(R.id.resultText);

        Button[] numberButtons = {
                findViewById(R.id.zero), findViewById(R.id.num1), findViewById(R.id.num2),
                findViewById(R.id.num3), findViewById(R.id.num4), findViewById(R.id.num5),
                findViewById(R.id.num6), findViewById(R.id.num7), findViewById(R.id.num8), findViewById(R.id.num9)
        };

        Button addButton = findViewById(R.id.add);
        Button subtractButton = findViewById(R.id.sub);
        Button multiplyButton = findViewById(R.id.mul);
        Button divideButton = findViewById(R.id.div);
        Button equalButton = findViewById(R.id.submit);
        Button clearButton = findViewById(R.id.clear_text);
        Button dotButton = findViewById(R.id.dot);

        for (Button button : numberButtons) {
            button.setOnClickListener(v -> editText.append(button.getText().toString()));
        }

        dotButton.setOnClickListener(v -> {
            if (!editText.getText().toString().contains(".")) {
                editText.append(".");
            }
        });

        addButton.setOnClickListener(v -> setOperator("+"));
        subtractButton.setOnClickListener(v -> setOperator("-"));
        multiplyButton.setOnClickListener(v -> setOperator("*"));
        divideButton.setOnClickListener(v -> setOperator("/"));

        clearButton.setOnClickListener(v -> {
            editText.setText("");
            resultText.setText("0");
        });

        equalButton.setOnClickListener(v -> calculateResult());
    }

    private void setOperator(String operator) {
        if (!editText.getText().toString().isEmpty()) {
            num1 = Double.parseDouble(editText.getText().toString());
            editText.setText("");
            switch (operator) {
                case "+": isAddition = true; break;
                case "-": isSubtraction = true; break;
                case "*": isMultiplication = true; break;
                case "/": isDivision = true; break;
            }
        }
    }

    private void calculateResult() {
        if (!editText.getText().toString().isEmpty()) {
            num2 = Double.parseDouble(editText.getText().toString());
            double result = isAddition ? num1 + num2 :
                    isSubtraction ? num1 - num2 :
                            isMultiplication ? num1 * num2 :
                                    (num2 != 0 ? num1 / num2 : Double.NaN);

            resultText.setText(String.valueOf(result));
            isAddition = isSubtraction = isMultiplication = isDivision = false;
        }
    }
}