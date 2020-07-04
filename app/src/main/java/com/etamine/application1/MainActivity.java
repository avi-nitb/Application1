package com.etamine.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.etamine.openapplicationlibrary.OpenApplicationForProcessing;



public class MainActivity extends AppCompatActivity {
    Button buttonAddition, buttonSubtract;
    EditText editTextFirstVariable, editTextSecondVariable;
    TextView textViewInputOne, textViewInputTwo, textViewAction, textViewResult;
    int result, inputOne, inputTwo;
    String operation="";
    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAddition = findViewById(R.id.buttonAddition);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        editTextFirstVariable = findViewById(R.id.editTextFirstVariable);
        editTextSecondVariable = findViewById(R.id.editTextSecondVariable);
        textViewInputOne = findViewById(R.id.textViewInputOne);
        textViewInputTwo = findViewById(R.id.textViewInputTwo);
        textViewAction = findViewById(R.id.textViewAction);
        textViewResult = findViewById(R.id.textViewResult);

        buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextFirstVariable.getText().toString().isEmpty() && !editTextSecondVariable.getText().toString().isEmpty()) {
                    bundle.putInt("FirstParameter", Integer.parseInt(editTextFirstVariable.getText().toString()));
                    bundle.putInt("SecondParameter", Integer.parseInt(editTextSecondVariable.getText().toString()));
                    bundle.putChar("Operation", '+');
                }
                OpenApplicationForProcessing.startApplication("com.etamine.application2", MainActivity.this, bundle);
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextFirstVariable.getText().toString().isEmpty() && !editTextSecondVariable.getText().toString().isEmpty()) {
                    bundle.putInt("FirstParameter", Integer.parseInt(editTextFirstVariable.getText().toString()));
                    bundle.putInt("SecondParameter", Integer.parseInt(editTextSecondVariable.getText().toString()));
                    bundle.putChar("Operation", '-');
                }
                OpenApplicationForProcessing.startApplication("com.etamine.application2", MainActivity.this, bundle);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        result = intent.getIntExtra("Result", 0);
        inputOne = intent.getIntExtra("FirstParameter", 0);
        inputTwo = intent.getIntExtra("SecondParameter", 0);
        operation = intent.getStringExtra("Action");

        //Setting Data to Views
       
            textViewInputOne.setText("Input One: " + inputOne);
            textViewInputTwo.setText("Input Two: " + inputTwo);
            textViewAction.setText("Action: " + operation);
            textViewResult.setText("Output: " + result);

    }
}
