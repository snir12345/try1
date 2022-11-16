package com.example.try1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText MedicationNameUp,MedicationTypeUp,MedicationCountUp;
    Button update_btn;

    String id, name, type, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MedicationNameUp = findViewById(R.id.MedicationNameUp);
        MedicationTypeUp = findViewById(R.id.MedicationTypeUp);
        MedicationCountUp = findViewById(R.id.MedicationCountUp);
        update_btn = findViewById(R.id.update_btn);

        //First we call this
        getAndSetIntentData();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDetabaceHelper myDB = new MyDetabaceHelper(UpdateActivity.this);
                name = MedicationNameUp.getText().toString();
                type = MedicationTypeUp.getText().toString();
                count = MedicationCountUp.getText().toString();
                myDB.updateData(name,type,count,id);

            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("type")&& getIntent().hasExtra("count")){
            //getting Data from intent.
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
            count = getIntent().getStringExtra("count");

            //setting intent data.
            MedicationNameUp.setText(name);
            MedicationTypeUp.setText(type);
            MedicationCountUp.setText(count);


        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

    }
}