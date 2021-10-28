package com.example.calorieconter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);
        SQLiteDatabase myR = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR.execSQL("CREATE TABLE IF NOT EXISTS Settings(uName VARCHAR,Age Integer, Height Integer, Weight VARCHAR, Gender VARCHAR);");

    }

    public void save_details(View view) {
        EditText myEdit=(EditText) findViewById(R.id.uName);
        String uName= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.Age);
        String Age= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.Height);
        String Height= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.Weight);
        String Weight= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.Gender);
        String Gender= myEdit.getText().toString();
        ContentValues values = new ContentValues();
        values.put("uName",uName);
        values.put("Age",Age);
        values.put("Height",Height);
        values.put("Weight",Weight);
        values.put("Gender",Gender);
        SQLiteDatabase myR = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR.insert("Settings",null,values);
        Intent Go_back = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(Go_back);
    }
}