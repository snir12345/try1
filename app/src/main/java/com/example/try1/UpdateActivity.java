package com.example.try1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText MedicationNameUp,MedicationTypeUp,MedicationCountUp;
    Button update_btn, delete_btn;

    String id, name, type, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MedicationNameUp = findViewById(R.id.MedicationNameUp);
        MedicationTypeUp = findViewById(R.id.MedicationTypeUp);
        MedicationCountUp = findViewById(R.id.MedicationCountUp);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        //First we call this
        getAndSetIntentData();

        //Set actionBar name after getAndSetIntentData method.
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

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
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + " ? ");
        builder.setMessage("Are you sure you want to delete " + name + " ? ");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDetabaceHelper myDB = new MyDetabaceHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}