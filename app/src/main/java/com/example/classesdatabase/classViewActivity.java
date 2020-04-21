package com.example.classesdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class classViewActivity extends AppCompatActivity {
    public TextView classText;
    public TextView teacherText;
    public TextView semesterText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        classText = findViewById(R.id.classText);
        teacherText = findViewById(R.id.teacherText);
        semesterText = findViewById(R.id.semesterText);
        databaseManager dbm = new databaseManager(this);
        Intent i = getIntent();
        String title = i.getStringExtra("TITLE");
        String[] entry = dbm.get(title);
        classText.setText(entry[0]);
        teacherText.setText(entry[1]);
        semesterText.setText(entry[2]);
    }
    public void editPressed(View v){
        Intent i = new Intent(this,addActivity.class);
        i.putExtra("ADD",false);
        i.putExtra("CLASS",classText.getText().toString());
        i.putExtra("TEACHER",teacherText.getText().toString());
        i.putExtra("SEMESTER",semesterText.getText().toString());
        startActivity(i);
        finish();
    }
    public void deletePressed(View v){
        databaseManager dbm = new databaseManager(this);
        dbm.deleteByName(classText.getText().toString());
        finish();
    }
}
