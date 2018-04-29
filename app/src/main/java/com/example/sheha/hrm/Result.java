package com.example.sheha.hrm;


/**
 * Created by sheha on 12/20/2016.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;
import java.util.Timer;


public class Result extends Fragment{
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    public static TextView result;
    private View rootView;
    private static final Random RANDOM = new Random();
    public static LineGraphSeries<DataPoint> series;
    public static int lastX = 0;
    public static GraphView graph;
    public static int beat = 0;
    boolean first = true;
    Timer t = new Timer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.result, container, false);

        result = (TextView) rootView.findViewById((R.id.result_field));
        // set up the graph instance
        graph = (GraphView) rootView.findViewById((R.id.graph));
        //data
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(300);
        viewport.setScrollable(true);
        viewport.isScalable();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                if(beat != 0) {
                    series.appendData(new DataPoint(lastX++, beat), true, 100);
                    beat = 0;
                }else
                    series.appendData(new DataPoint(lastX++, 10), true, 100);
                mHandler.postDelayed(this, 500);
            }
        };
        mHandler.postDelayed(mTimer1, 500);
    }

     //add random data to graph
     public static void addEntry(int aBeat) {
         beat = aBeat;
     }
}