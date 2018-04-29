package com.example.sheha.hrm;

/**
 * Created by sheha on 12/20/2016.
 */

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainTab extends Fragment {
    public boolean isButtonClicked() {
        return buttonClicked;
    }

    public void setButtonClicked(boolean buttonClicked) {
        this.buttonClicked = buttonClicked;
    }

    private boolean buttonClicked = false;
    private ProgressBar prg;
    MainActivity m;
    ConnectThread ct;
    blutooth blue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.main_tab, container, false);


        prg = (ProgressBar)rootView.findViewById((R.id.progressBar1));

        final Button main_button = (Button) rootView.findViewById(R.id.main_button);
        main_button.setOnClickListener(
                new Button.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    public void onClick(View v) {
                        buttonClicked = !buttonClicked;

                        TextView DynamicText = (TextView) rootView.findViewById(R.id.DynamicText);
                        if(buttonClicked)
                        {
                            DynamicText.setText("Please swipe left for result");
                            prg.setVisibility(View.VISIBLE);
                            main_button.setText("Cancel");
                            ct = new ConnectThread(blue.bt,blue.mHandler);
                            ct.start();
                        }
                        else
                        {
                            DynamicText.setText("Please click the button below to start");
                            main_button.setText("Start");
                            prg.setVisibility(View.GONE);
                            ct.cancel();
                        }

                    }
                }

        );

        return rootView;
    }
}