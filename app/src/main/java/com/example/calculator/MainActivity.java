package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Integer firstOperand, secondOperand, sum;
    private Boolean isOperationFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

    }

    public void onNumberClick(View view) {

        String number = ((MaterialButton)view).getText().toString();

        if (view.getId() == R.id.clearBT) {
            textView.setText("0");
            firstOperand = 0;
            secondOperand = 0;
            sum = 0;
        } else if(textView.getText().toString().equals("0") || isOperationFinished){
            textView.setText(number);
        } else {
            textView.append(number);
        }
        isOperationFinished = false;
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.plusBT) {
            firstOperand = Integer.valueOf(textView.getText().toString());
            textView.append("+");
        } else if (view.getId() == R.id.equalBT) {
            if (!textView.getText().toString().equals("0")) {
                String secondText = textView.getText()
                        .toString().replace("+", " ");
                if (secondText.contains(" ")) {
                    String [] arrays = secondText.split(" ");
                    secondOperand = Integer.valueOf(arrays[1]);
                }
                sum = firstOperand + secondOperand;
                textView.setText(sum.toString());
                isOperationFinished = true;
            }
        }
    }
}