package com.example.classesdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity {
    public EditText classesText;
    public EditText teacherText;
    public EditText semesterText;
    public Button addButton;
    public boolean add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        classesText = findViewById(R.id.classText);
        teacherText = findViewById(R.id.teacherText);
        semesterText = findViewById(R.id.semesterText);
        addButton = findViewById(R.id.addButton);
        Intent i = getIntent();
        add = i.getBooleanExtra("ADD",true);
        if(add){
            addButton.setText("ADD");
        }else{
            addButton.setText("Edit");
            classesText.setText(i.getStringExtra("CLASS"));
            teacherText.setText(i.getStringExtra("TEACHER"));
            semesterText.setText(i.getStringExtra("SEMESTER"));
        }

    }
    public void addClass(View v) {
        String classTitle = classesText.getText().toString();
        String teacherTitle = teacherText.getText().toString();
        String semester = semesterText.getText().toString();
        databaseManager dbm = new databaseManager(this);
        if (add)
            dbm.insert(classTitle, teacherTitle, semester);
        else
            dbm.updateByTitle(classTitle,teacherTitle);
        finish();
    }
}
