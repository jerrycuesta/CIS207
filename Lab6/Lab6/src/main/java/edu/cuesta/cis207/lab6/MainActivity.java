package edu.cuesta.cis207.lab6;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    TextView textMessage;
    EditText editMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textMessage = (TextView) findViewById(R.id.textMessage);
        editMessage = (EditText) findViewById(R.id.editMessage);

        final Button button = (Button) findViewById(R.id.buttonChange);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newText = editMessage.getText().toString();
                textMessage.setText(newText);
                editMessage.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editMessage.getWindowToken(), imm.HIDE_IMPLICIT_ONLY);
            }
        });


    }
}
