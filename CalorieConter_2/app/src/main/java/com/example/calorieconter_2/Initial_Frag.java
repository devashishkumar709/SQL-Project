package com.example.calorieconter_2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.xml.sax.DTDHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.PieChartView;

public class Initial_Frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){


        return inflater.inflate(R.layout.initial_frag,container,false);
    }
    public void onStart() {
        super.onStart();
        int[] Data = MainActivity.MC;
        PieChartView PieChart ;
        PieChart = (PieChartView) getView().findViewById(R.id.Pie);
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(Data[0], Color.BLUE).setLabel("BreakFast"));
        pieData.add(new SliceValue(Data[1],Color.GREEN).setLabel("Lunch"));
        pieData.add(new SliceValue(Data[2], Color.RED).setLabel("Dinner"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Calories Consumed Today").setCenterText1FontSize(12).setCenterText1Color(Color.parseColor("#0097A7"));
        PieChart.setPieChartData(pieChartData);
    }
}

