package com.example.h8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView bottleWindow;
    TextView eventWindow;
    TextView moneyWindow;
    SeekBar moneySeekBar;
    TextView showSeekBarMoney;
    Spinner bottleMenu;
    Context context = null;

    double money;

    BottleDispenser bottledispenser = BottleDispenser.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        bottleWindow = (TextView) findViewById(R.id.bottleWindow);

        bottledispenser.listBottles(bottleWindow);

        moneySeekBar = (SeekBar) findViewById(R.id.seekBar);
        showSeekBarMoney = (TextView) findViewById(R.id.showSeekBarMoney);


        moneySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                showSeekBarMoney.setText("Add: " + (double) progress/100 + " €");
                money = (double) progress/100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        bottleMenu = (Spinner) findViewById(R.id.bottleMenu);
        ArrayAdapter<Bottle> adapter = new ArrayAdapter<Bottle>(this, android.R.layout.simple_spinner_item, BottleDispenser.bottle_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bottleMenu.setAdapter(adapter);

    }

    public void addMoney(View v) {
        eventWindow = (TextView) findViewById(R.id.eventWindow);
        moneyWindow = (TextView) findViewById(R.id.moneyWindow);
        bottledispenser.addMoney(eventWindow, moneyWindow, money);
        moneySeekBar.setProgress(0);
    }

    public void buyBottle(View v) {
        eventWindow = (TextView) findViewById(R.id.eventWindow);
        moneyWindow = (TextView) findViewById(R.id.moneyWindow);
        //Bottle bottle = (Bottle) bottleMenu.getSelectedItem();
        int loc = bottleMenu.getSelectedItemPosition();
        bottledispenser.buyBottle(eventWindow, moneyWindow, loc);
    }

    public void returnMoney(View v) {
        eventWindow = (TextView) findViewById(R.id.eventWindow);
        moneyWindow = (TextView) findViewById(R.id.moneyWindow);
        bottledispenser.returnMoney(eventWindow, moneyWindow);
    }

    public void printReceipt(View v) {
        eventWindow = (TextView) findViewById(R.id.eventWindow);
        bottledispenser.writeReceipt(eventWindow, context);

    }

}
