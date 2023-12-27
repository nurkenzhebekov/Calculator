package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView operationTV;
    private Integer firstOperand, secondOperand, result;
    private Boolean isOperationFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationTV = findViewById(R.id.operationTV);

    }

    public void onNumberClick(View view) {

        String number = ((MaterialButton)view).getText().toString();

        if (view.getId() == R.id.acBT) {
            operationTV.setText("0");
            firstOperand = 0;
            secondOperand = 0;
            result = 0;
        } else if(operationTV.getText().toString().equals("0") || isOperationFinished){
            operationTV.setText(number);
        } else {
            operationTV.append(number);
        }
        isOperationFinished = false;
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.plusBT) {
            firstOperand = Integer.valueOf(operationTV.getText().toString());
            operationTV.append("+");
        } else if (view.getId() == R.id.minusBT) {
            firstOperand = Integer.valueOf(operationTV.getText().toString());
            operationTV.append("-");
        } else if (view.getId() == R.id.multiplyBT) {
            firstOperand = Integer.valueOf(operationTV.getText().toString());
            operationTV.append("×");
        } else if (view.getId() == R.id.divideBT) {
            firstOperand = Integer.valueOf(operationTV.getText().toString());
            operationTV.append("÷");
        }
        isOperationFinished = false;
    }

    public void onEqualClick(View view) {
        if (!operationTV.getText().toString().equals("0")) {
            String[] operands = operationTV.getText().toString().split("[-+×÷]");
            if (operands.length > 1) {
                secondOperand = Integer.valueOf(operands[1]);
                if (operationTV.getText().toString().startsWith("-")) {
                    secondOperand = -secondOperand;
                }
            } else {
                Log.e("Calculator", "Error: Could not extract second operand.");
                return;
            }
            if (operationTV.getText().toString().contains("+")) {
                result = firstOperand + secondOperand;
            } else if (operationTV.getText().toString().contains("-")) {
                result = firstOperand - secondOperand;
            } else if (operationTV.getText().toString().contains("×")) {
                result = firstOperand * secondOperand;
            } else if (operationTV.getText().toString().contains("÷")) {
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    showZeroError("Division by zero is not allowed.");
                    return;
                }
            }
            operationTV.setText(result.toString());
            isOperationFinished = true;
        }
    }

    private void showZeroError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}