package com.example.h10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    WebView web;
    Button searchBtn, refreshBtn, forwardBtn, backwardBtn;
    SearchView browser;
    ListIterator<String> iterator;
    String URL = "http://www.google.fi";
    int flag = 1;
    public static ArrayList<String> browse_history = new ArrayList<String>(); {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        searchBtn = (Button) findViewById(R.id.search);
        refreshBtn = (Button) findViewById(R.id.refresh);
        forwardBtn = (Button) findViewById(R.id.forwardBtn);
        backwardBtn = (Button) findViewById(R.id.backwardBtn);
        web = (WebView) findViewById(R.id.webView);
        browser = (SearchView) findViewById(R.id.browser);
        iterator = browse_history.listIterator();

        browser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String url) {
                URL = url.replace("http://", "").replace("https://","");
                if (URL.equals("index.html")){
                    web.loadUrl("file:///android_asset/index.html");
                } else {
                    URL = "http://" + URL;
                    web.loadUrl(URL);
                    deleteURLS();
                    addURL(URL);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(URL);
        addURL(URL);
    }

    public void searchBtn(View v) {
        URL = browser.getQuery().toString().replace("http://", "").replace("https://","");
        if (URL.equals("index.html")){
            web.loadUrl("file:///android_asset/index.html");
        } else {
            URL = "http://" + URL;
            web.loadUrl(URL);
            browser.setQuery(URL,false);
            deleteURLS();
            addURL(URL);
        }
    }

    public void refresh(View v) {
        if (URL.equals("index.html")){
            web.loadUrl("file:///android_asset/index.html");
        } else {
            URL = web.getUrl();
            web.loadUrl(URL);
            browser.setQuery(URL,true);
            addURL(URL);
        }
    }

    public void readHTML(View v) {
        if (flag == 1) {
            web.evaluateJavascript("javascript:shoutOut()",null);
            flag = 2;
        }
        else if (flag == 2) {
            web.evaluateJavascript("javascript:initialize()", null);
            flag = 1;
        }
    }

    public void goForward(View v) {
        if (iterator.hasNext()) {
            if (!iterator.hasPrevious()) {
                iterator.next();
            }
            URL = iterator.next();
            web.loadUrl(URL);
        }
    }

    public void goBackward(View v) {
        if (iterator.hasPrevious()) {
            if (!iterator.hasNext()) {
                iterator.previous();
            }
            URL = iterator.previous();
            web.loadUrl(URL);
        }
    }

    public void addURL(String url){
        if (browse_history.size() == 10) {
            while (iterator.hasPrevious()) {
                iterator.previous();
                if (!iterator.hasPrevious()) {
                    iterator.remove();
                }
            } while (iterator.hasNext()) {
                iterator.next();
            }
        }
        iterator.add(url);
        System.out.println(browse_history);
        System.out.println(browse_history.size());
    }

    public void deleteURLS() {
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }


}
