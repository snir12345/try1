package com.example.try1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    TextView NoData;
    ImageView empty_imageView;

    MyDetabaceHelper myDB;
    ArrayList<String> Medication_id,Medication_Type,Medication_Name,Medication_Count;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        empty_imageView = findViewById(R.id.empty_imageView);
        NoData = findViewById(R.id.NoData);
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

        customAdapter = new CustomAdapter(MainActivity.this, this,Medication_id,Medication_Type,Medication_Name,Medication_Count);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataArrays (){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0 ){
            empty_imageView.setVisibility(View.VISIBLE);
            NoData.setVisibility(View.VISIBLE);
        }
        else{
            while (cursor.moveToNext()){
                Medication_id.add(cursor.getString(0));
                Medication_Name.add(cursor.getString(1));
                Medication_Type.add(cursor.getString(2));
                Medication_Count.add(cursor.getString(3));
            }
            empty_imageView.setVisibility(View.GONE);
            NoData.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?" );
        builder.setMessage("Are you sure you want to delete all Data? ");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDetabaceHelper myDB = new MyDetabaceHelper(MainActivity.this);
                myDB.deleteAllData();
                // Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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