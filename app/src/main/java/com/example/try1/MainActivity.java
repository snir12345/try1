package com.example.try1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_btn;

    MyDetabaceHelper myDB;
    ArrayList<String> Medication_id,Medication_Type,Medication_Name,Medication_Count;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDetabaceHelper(MainActivity.this);
        Medication_id = new ArrayList<>();
        Medication_Type = new ArrayList<>();
        Medication_Name = new ArrayList<>();
        Medication_Count = new ArrayList<>();

        storeDataArrays();

        customAdapter = new CustomAdapter(MainActivity.this, Medication_id,Medication_Type,Medication_Name,Medication_Count);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



    }
    void storeDataArrays (){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0 ){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                Medication_id.add(cursor.getString(0));
                Medication_Name.add(cursor.getString(1));
                Medication_Type.add(cursor.getString(2));
                Medication_Count.add(cursor.getString(3));
            }
        }
    }
}