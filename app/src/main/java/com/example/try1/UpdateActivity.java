package com.example.try1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText MedicationNameUp,MedicationTypeUp,MedicationCountUp;
    Button update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MedicationNameUp = findViewById(R.id.MedicationNameUp);
        MedicationTypeUp = findViewById(R.id.MedicationTypeUp);
        MedicationCountUp = findViewById(R.id.MedicationCountUp);
        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    void
}