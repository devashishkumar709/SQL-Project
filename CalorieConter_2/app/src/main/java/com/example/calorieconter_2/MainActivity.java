package com.example.calorieconter_2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    static int [] MC = {0,0,0};
    static char meal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        SQLiteDatabase myR3 = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR3.execSQL("CREATE TABLE IF NOT EXISTS Settings(uName VARCHAR,Age Integer, Height Integer, Weight VARCHAR, Gender VARCHAR);");
        SQLiteDatabase myDB_1 = getApplicationContext().openOrCreateDatabase("Recipie", MODE_PRIVATE, null );
        Cursor F = myDB_1.rawQuery("select * from Settings", null);
        String n="";
        while(F.moveToNext()){
            n=F.getString(0);
        }
        TextView U_name= (TextView)  findViewById(R.id.textView2);
        U_name.setText(n);
        if (n==""){
            U_name.setText("NAME");
        }
        addfragment();
        SQLiteDatabase myR = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR.execSQL("CREATE TABLE IF NOT EXISTS Entries(rName VARCHAR,calories Integer, protiens Integer, Carbs Integer, Fats Integer,Meal VARCHAR, DT VARCHAR);");
        LocalDateTime obj =LocalDateTime.now();
        DateTimeFormatter fobj= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = obj.format(fobj);
        int Compare = (int)formattedDate.charAt(0);
        Compare -= 48;
        Compare *= 10;
        Compare += formattedDate.charAt(1);
        Compare -= 48;
        Cursor D = myR.rawQuery("select * from Entries",null);
        while(D.moveToNext()){
            String for_Compare = D.getString(6);
            int Current = for_Compare.charAt(0); Current -=48; Current *=10;
            Current += for_Compare.charAt(1);
            Current -=48;
//                System.out.println(Current+" Out if");
            if(Compare==Current){
                System.out.println("Matched");
                int A =(int) D.getString(5).charAt(0);
                if(A== 66){
                    String Required = D.getString(1);
                    int Cal=0;
                    for(int i=0;i<Required.length();i++){
                        Cal*=10;
                        Cal += (int) Required.charAt(i);
                        Cal -= 48;
                    }
                    MC[0] += Cal;
                    System.out.println(D.getString(1));
                }
                if(A== 76){
                    String Required = D.getString(1);
                    int Cal=0;
                    for(int i=0;i<Required.length();i++){
                        Cal*=10;
                        Cal += (int) Required.charAt(i);
                        Cal -= 48;
                    }
                    MC[1] += Cal;
                    System.out.println("Lunch"+Cal);
                }
                if(A== 68){
                    String Required = D.getString(1);
                    int Cal=0;
                    for(int i=0;i<Required.length();i++){
                        Cal*=10;
                        Cal += (int) Required.charAt(i);
                        Cal -= 48;
                    }
                    MC[2] += Cal;
                }
            }
        }

    }

    public void settings(View view) {
        Intent set = new Intent(getApplicationContext(),Settings.class);
        startActivity(set);

    }
    public void SaveRecord(View view){
        EditText myEdit=(EditText) findViewById(R.id.RN);
        String RN= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.CAL);
        String CAL= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.PR);
        String PR= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.CARBS);
        String CARBS= myEdit.getText().toString();
        myEdit=(EditText) findViewById(R.id.FATS);
        String FATS= myEdit.getText().toString();
        LocalDateTime obj =LocalDateTime.now();
        DateTimeFormatter fobj= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = obj.format(fobj);
        SQLiteDatabase myR = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR.execSQL("CREATE TABLE IF NOT EXISTS Entries(rName VARCHAR,calories Integer, protiens Integer, Carbs Integer, Fats Integer,Meal VARCHAR, DT VARCHAR);");
        ContentValues values = new ContentValues();
        values.put("rName",RN);
        values.put("calories",CAL);
        values.put("protiens",PR);
        values.put("Carbs",CARBS);
        values.put("Fats",FATS);
        values.put("Meal", String.valueOf(meal));
        values.put("DT", formattedDate);
        myR = openOrCreateDatabase("Recipie",MODE_PRIVATE,null);
        myR.insert("Entries",null,values);
        addfragment2();
        this.recreate();
    }


    private void addfragment(){
        Initial_Frag sample =new Initial_Frag();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.frag,sample);
        fragmentTransaction.commit();
    }
    private void addfragment2(){
        Initial_Frag sample =new Initial_Frag();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,sample);
        fragmentTransaction.commit();
    }


    public void Recipie(View view) {
        Intent set = new Intent(getApplicationContext(),Recipie.class);
        startActivity(set);
    }

    public void Add_break(View view) {
        meal='B';
        Entries e= new Entries();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,e);
        fragmentTransaction.commit();
    }

    public void Add_Lunch(View view) {
        meal='L';
        Entries e= new Entries();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,e);
        fragmentTransaction.commit();
    }

    public void Add_Dinner(View view) {
        meal='D';
        Entries e= new Entries();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,e);
        fragmentTransaction.commit();
    }


    public void User_Data(View view) {
        Intent U = new Intent(getApplicationContext(),Metrics.class);
        startActivity(U);
    }
}