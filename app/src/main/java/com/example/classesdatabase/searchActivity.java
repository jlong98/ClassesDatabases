package com.example.classesdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class searchActivity extends AppCompatActivity {
    public ScrollView scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        scroll = findViewById(R.id.scroll);
        Intent i = getIntent();
        String dName = i.getExtras().getString("DNAME");
        scroll = findViewById(R.id.scroll);
        scroll.removeAllViewsInLayout();
        databaseManager dbm = new databaseManager(this);
        ArrayList<String> arr = dbm.getD(dName);
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(arr.size());
        for(String title : arr){
            TextView text = new TextView(this);
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
        scroll.addView(grid);
    }
}


