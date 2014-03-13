package com.example.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private TextView textResult;
    private EditText editNum1;
    private EditText editNum2;

    enum Operation { ADD, SUBTRACT }

    private int GetNumber(EditText editText)
    {
        return Integer.parseInt(editText.getText().toString());
    }

    private void UpdateResult(Operation op)
    {
        String resultText;
        try
        {
            Integer result = 0;

            switch (op)
            {
                case ADD:
                    result = GetNumber(editNum1) + GetNumber(editNum2);
                    break;

                case SUBTRACT:
                    result = GetNumber(editNum1) - GetNumber(editNum2);
                    break;
            }

            resultText = result.toString();
        }
        catch (Exception ex)
        {
            resultText = "Overflow";
        }

        textResult.setText(resultText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = (TextView) findViewById(R.id.textResult);
        editNum1 = (EditText) findViewById(R.id.editText1);
        editNum2 = (EditText) findViewById(R.id.editText2);

        editNum1.setText("0");
        editNum2.setText("0");
        textResult.setText("0");

        final Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateResult(Operation.ADD);
            }
        });

        final Button buttonSub = (Button) findViewById(R.id.buttonSubtract);
        buttonSub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateResult(Operation.SUBTRACT);
            }
        });
    }
}
