package com.example.h9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    EditText getLocation, getHours, getDays, getPickUpTime;
    Spinner SmartPosts;
    Context context = null;

    SmartPostApp spa = new SmartPostApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getLocation = (EditText) findViewById(R.id.getLoc);
        getHours = (EditText) findViewById(R.id.getOpenHours);
        getDays = (EditText) findViewById(R.id.getOpenDays);
        getPickUpTime = (EditText) findViewById(R.id.getPickUpTime);

        spa.readXMLFIN();
        spa.readXMLEST();

        SmartPosts = (Spinner) findViewById(R.id.smartPostSpinner);
        ArrayAdapter<SmartPost> adapter = new ArrayAdapter<SmartPost>(this, android.R.layout.simple_spinner_dropdown_item,SmartPostApp.smartPostArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SmartPosts.setAdapter(adapter);

    }

    public void searchPosts(View v) {
        ArrayAdapter<SmartPost> adapter = new ArrayAdapter<SmartPost>(this, android.R.layout.simple_spinner_dropdown_item,SmartPostApp.smartPostArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SmartPosts.setAdapter(adapter);
        SmartPosts.refreshDrawableState();

        if ((getLocation.getText().toString().equals("Finland")) == true) {
            SmartPostApp.smartPostArrayList.clear();
            spa.readXMLFIN();

        } if((getLocation.getText().toString().equals("Estonia")) == true) {
            SmartPostApp.smartPostArrayList.clear();
            spa.readXMLEST();

        } if ((getLocation.getText().toString().equals("") ||(getLocation.getText().toString().equals("Finland")) || (getLocation.getText().toString().equals("Estonia"))) == false) {
            Toast.makeText(this, "Unknown search, try again.", Toast.LENGTH_SHORT).show();

        }
    }

    public void showInfo(View v) {
        SmartPost smartpost = (SmartPost) SmartPosts.getSelectedItem();
        Toast.makeText(this, smartpost.getName() + "\n" + smartpost.getAddress() + ", " + smartpost.getCity() + "\n" + smartpost.getAvailability(), Toast.LENGTH_LONG).show();
    }

    public void refresh(View v) {
        getLocation.setText("");
        getHours.setText("");
        getDays.setText("");
        getPickUpTime.setText("");
        SmartPostApp.smartPostArrayList.clear();
        spa.readXMLFIN();
        spa.readXMLEST();
        ArrayAdapter<SmartPost> adapter = new ArrayAdapter<SmartPost>(this, android.R.layout.simple_spinner_dropdown_item,SmartPostApp.smartPostArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SmartPosts.setAdapter(adapter);
        SmartPosts.refreshDrawableState();
    }
}
