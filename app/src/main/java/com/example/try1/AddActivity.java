package com.example.try1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    Button save_btn;
    EditText MedicationType,MedicationName,MedicationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        MedicationName = findViewById(R.id.MedicationName);
        MedicationType = findViewById(R.id.MedicationType);
        MedicationCount = findViewById(R.id.MedicationCount);
        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDetabaceHelper myDB = new MyDetabaceHelper(AddActivity.this);
                myDB.addMedication(MedicationName.getText().toString().trim(),
                        MedicationType.getText().toString().trim(),
                        Integer.valueOf( MedicationCount.getText().toString().trim()));
                recreate();
            }
        });
    }


}