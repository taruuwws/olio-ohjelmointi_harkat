package com.example.h7t5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText filename;
    EditText filetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        filename = (EditText) findViewById(R.id.readFileName);
        filetext = (EditText) findViewById(R.id.readWriteFileText);
    }

    public void readFile(View v) {
        try {
            InputStream ins = context.openFileInput(filename.getText().toString());

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null) {
                filetext.setText(s);
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }
    }

    public void writeFile(View v) {
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(filename.getText().toString(),Context.MODE_PRIVATE));

            String s = filetext.getText().toString();
            ows.write(s);
            ows.close();

            filename.setText("");
            filetext.setText("");

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }
    }
}
