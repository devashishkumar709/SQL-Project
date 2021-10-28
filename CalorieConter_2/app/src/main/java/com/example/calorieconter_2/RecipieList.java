package com.example.calorieconter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RecipieList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_list);

        Cursor C;
        getApplicationContext();
        SQLiteDatabase myDB = getApplicationContext().openOrCreateDatabase("Recipie", MODE_PRIVATE, null );
        C = myDB.rawQuery("select * from R", null);

        if (C.getCount()==0){
            Toast t = Toast.makeText(getApplicationContext(),"No Items to Display",Toast.LENGTH_LONG);
            t.show();
        }
        else{
            TextView TB = (TextView)findViewById(R.id.rList);
            StringBuffer SB = new StringBuffer();
            while (C.moveToNext()){
                SB.append("Food Item Names: "+ C.getString(0)+"\n");
                SB.append("Calories: "+C.getString(1)+" Protiens: "+C.getString(2)+" Carbs: "+C.getString(3)+" Fats: "+C.getString(4)+"\n");
                SB.append("\n");
                TB.setText(SB.toString());

            }
        }
    }
}