package com.example.try1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDetabaceHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MedicationList";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Medication_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PILL = "Medication_name";
    private static final String CULUMN_TYPE ="Medicine_type";
    private static final String CULUMN_COUNT = "Medicine_detail";




    public MyDetabaceHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                        " ( " +  COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PILL + " TEXT, " +
                        CULUMN_TYPE + " TEXT, " +
                        CULUMN_COUNT + " INTEGER ) ;";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addMedication(String pill, String type, int count ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put( COLUMN_PILL, pill);
        cv.put( CULUMN_TYPE, type);
        cv.put( CULUMN_COUNT, count);
        long result = db.insert(TABLE_NAME, null,cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!= null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }
}
