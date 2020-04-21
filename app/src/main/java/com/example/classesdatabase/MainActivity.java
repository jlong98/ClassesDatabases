package com.example.classesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Button addButton;
    public Button searchButton;
    public EditText searchText;
    public ScrollView scrollview;
    static Boolean runOnPause = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.searchButton);
        searchText = findViewById(R.id.searchText);
        scrollview = findViewById(R.id.scrollView);
        scrollview.removeAllViewsInLayout();
        createView();
    }
    public void addPressed(View v){
        Intent i = new Intent(this, addActivity.class);
        i.putExtra("ADD",true);
        startActivity(i);

    }
    public void searchPressed(View v){
        String dName = searchText.getText().toString();
        Intent i = new Intent(this, searchActivity.class);
        i.putExtra("DNAME",dName);
        startActivity(i);

    }
    public void createView(){
        final databaseManager dbm = new databaseManager(this);
        final ArrayList<String> list = dbm.getTitles();
        final GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(list.size());
        for(String title : list){
            final TextView text = new TextView(this);
            text.setText(title);
            text.setTextSize(40);
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), classViewActivity.class);
                    i.putExtra("TITLE", ((TextView) v).getText().toString());
                    startActivity(i);
                }
            });
            grid.addView(text);
        }

        scrollview.addView(grid);
    }
    // This is what I was working on to try and skip onResume being run on startup but androidStudio didn't like this.
    /*
    public void onResume(){
        if(runOnPause){
            super.onResume();
            createView();
        }else{
            runOnPause = true;
        }
    }
    */
}
