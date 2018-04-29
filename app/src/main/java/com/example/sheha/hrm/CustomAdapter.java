package com.example.sheha.hrm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String>{
    public CustomAdapter(Context context, String[] beats) {
        super(context, R.layout.custom_row ,beats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // default -  return super.getView(position, convertView, parent);
        // add the layout
        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.custom_row, parent, false);
        // get references.
        String beatsLog = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.logTxt);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.imageView);

        // dynamically update the text from the array
        itemText.setText(beatsLog);
        // using the same image every time
        buckysImage.setImageResource(R.drawable.heartbeat);
        // Now we can finally return our custom View or custom item
        return customView;
    }
}