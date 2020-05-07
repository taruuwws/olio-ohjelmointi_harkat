package com.example.h7t1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextView text = (TextView) findViewById(R.id.textView);
        text = (TextView) findViewById(R.id.textView);
        edit = (EditText) findViewById(R.id.editText);

    }


    public void printText(View v) {
        //System.out.println("Hello World!");
        //text.setText("Hello World !");
        String Text = edit.getText().toString();
        text.setText(Text);
    }
}
