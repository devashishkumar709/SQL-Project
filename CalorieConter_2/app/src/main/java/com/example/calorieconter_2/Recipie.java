package com.example.calorieconter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Recipie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recipie);
        SQLiteDatabase myR = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR.execSQL("CREATE TABLE IF NOT EXISTS R(rName VARCHAR,calories Integer, protiens Integer, Carbs Integer, Fats Integer);");
    }



    public void Insert(View view) {
        EditText myEdit=(EditText) findViewById(R.id.rName);
        String Recipies= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.rCal);
        String calories= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.rPro);
        String protiens= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.rCarbs);
        String Carbs= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.rFats);
        String Fats= myEdit.getText().toString();
        ContentValues values = new ContentValues();
        values.put("rName",Recipies);
        values.put("calories",calories);
        values.put("protiens",protiens);
        values.put("Carbs",Carbs);
        values.put("Fats",Fats);
        SQLiteDatabase myR = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR.insert("R",null,values);
        Intent Go_back = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(Go_back);

    }
    public void List (View view){
        Intent i = new Intent(getApplicationContext(), RecipieList.class);
        startActivity(i);
    }
}