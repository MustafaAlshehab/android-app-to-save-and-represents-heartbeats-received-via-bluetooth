package com.example.sheha.hrm;

/**
 * Created by sheha on 12/20/2016.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class log extends Fragment{
    public static ListView LogView;
    public static ListAdapter logAdapter;
    public static ArrayList<String> beats = new ArrayList<String>();
    public static Context c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.log, container, false);

       // beats.add("");
        c = getContext();
        logAdapter = new CustomAdapter(getContext(), beats.toArray(new String[beats.size()]));
        LogView = (ListView) rootView.findViewById(R.id.loglist);
        LogView.setAdapter(logAdapter);

        return rootView;
    }

    public static void addABeat(String aBeat){
        beats.add(aBeat);
        logAdapter = new CustomAdapter(c, beats.toArray(new String[beats.size()]));
        LogView.setAdapter(logAdapter);
    }
}