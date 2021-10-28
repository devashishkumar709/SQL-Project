package com.example.calorieconter_2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Entries extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_entries,container,false);

    }
    public void SaveRecord(View view){
//        Toast T= Toast.makeText(getContext(),"Hi",Toast.LENGTH_LONG);
        System.out.println("Enter");
    }



}
