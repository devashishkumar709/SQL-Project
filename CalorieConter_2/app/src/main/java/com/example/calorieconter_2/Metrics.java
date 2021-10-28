package com.example.calorieconter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class Metrics extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_metrics);
        LineChartView lineChartView = findViewById(R.id.LineChart);
        String[] AxisData = new String[7];
        int[] Y= new int[7];
        SQLiteDatabase myDB_1 = getApplicationContext().openOrCreateDatabase("Recipie", MODE_PRIVATE, null );
        Cursor D = myDB_1.rawQuery("select * from Entries", null);
        LocalDateTime obj =LocalDateTime.now();
        DateTimeFormatter fobj= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = obj.format(fobj);
        int Compare = (int)formattedDate.charAt(0);
        Compare -= 48;
        Compare *= 10;
        Compare += formattedDate.charAt(1);
        Compare -= 48;
        Compare -= 6;
        for(int i=0;i<7;i++){
            int index=0;
            Cursor E = myDB_1.rawQuery("select * from Entries", null);
            while (E.moveToNext()){
                String for_Compare = E.getString(6);
                int Current = for_Compare.charAt(0); Current -=48; Current *=10;
                Current += for_Compare.charAt(1);
                Current -=48;
//                System.out.println(Current+" Out if");
                if(Compare==Current){
//                    System.out.println("Current4 "+Current);
                    String Current_Cal= E.getString(1);
//                    System.out.println(Current+" Current");
                    int Cal_here =0;
                    for(int j=0;j<Current_Cal.length();j++){
                        Cal_here *=10;
                        Cal_here +=(Current_Cal.charAt(j))-48;
                    }
                    index +=Cal_here;
                }
            }
            Y[i] = index;
//            System.out.println(index+" index "+i);
            AxisData [i] = ""+Compare;
            Compare +=1;
        }
        List YAxisValues =new ArrayList();
        List AxisValues = new ArrayList();
        Line line = new Line(YAxisValues).setColor(Color.parseColor("#008080"));
        for (int i=0;i<AxisData.length;i++){
            AxisValues.add(i,new AxisValue(i).setLabel(AxisData[i]));
        }
        for (int i=0;i<Y.length;i++){
            YAxisValues.add(new PointValue(i,Y[i]/10));
        }
        List lines = new ArrayList();
        lines.add(line);
        LineChartData Data = new LineChartData();
        Data.setLines(lines);
        Axis axis = new Axis();
        axis.setValues(AxisValues);
        Data.setAxisXBottom(axis);
        Axis Yaxis = new Axis();
        Data.setAxisYLeft(Yaxis);
        lineChartView.setLineChartData(Data);
        Viewport viewport =new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 420;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);


        Cursor C;
        getApplicationContext();
        SQLiteDatabase myDB = getApplicationContext().openOrCreateDatabase("Recipie", MODE_PRIVATE, null );
            C = myDB.rawQuery("select * from Entries", null);

        if (C.getCount()==0){
            System.out.println("AAgya andar");
        }
        else{
            TextView TB = (TextView)findViewById(R.id.Metrics1);
            StringBuffer SB = new StringBuffer();
            while (C.moveToNext()){
                SB.append("Food Item Names: "+ C.getString(0)+"\n");
                SB.append("Calories: "+C.getString(1)+" Protiens: "+C.getString(2)+" Carbs: "+C.getString(3)+" Fats: "+C.getString(4)+"\n");
                SB.append("Meal Time: "+ C.getString(5));
                //match date and time for current day
//                if (C.getString(5)=="B"){
//                    SB.append("Breakfast");
//                }
//                else if (C.getString(5)=="L"){
//                    SB.append("Lunch");
//                }
//                else if (C.getString(5)=="D"){
//                    SB.append("Dinner");
//                }
                SB.append(" Date & Time: "+C.getString(6)+"\n");
                SB.append("\n");
                TB.setText(SB.toString());

            }
        }
    }

}