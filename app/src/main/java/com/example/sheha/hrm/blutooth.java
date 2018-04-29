package com.example.sheha.hrm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class blutooth extends AppCompatActivity {

    Button b1,b2,b3;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice>pairedDevices;
    ListView lv;
    ConnectThread ct;
    public static BluetoothDevice bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_blutooth);
        b1 = (Button) findViewById(R.id.button5);
        b2 = (Button) findViewById(R.id.button6);
        b3 = (Button) findViewById(R.id.button7);
        lv = (ListView) findViewById(R.id.listView);
    }
    public void bluetoothSupport(View v){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Device does not support Bluetooth",Toast.LENGTH_LONG).show();
            // Device does not support Bluetooth
            }

    }
    // a handler to deliver the messages recived via Bluetooth to the UI
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] writeBuf = (byte[]) msg.obj;
            int begin = (int) msg.arg1;
            int end = (int) msg.arg2;
            switch (msg.what) {
                case 1:
                   String writeMessage = new String(writeBuf);
                    writeMessage = writeMessage.substring(begin, end);
                    Result.result.setText(writeMessage);
                    log.addABeat(writeMessage);
                    try {
                        System.out.println(writeMessage);
                        Result.addEntry(Integer.parseInt(writeMessage.trim()));
                    }catch (NumberFormatException e){}
                    break;
            }
        }
    };
   public void OnOff(View v){
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0); // local int has to be >= 0
            Toast.makeText(getApplicationContext(), "Bluetooth Enabled",Toast.LENGTH_LONG).show();
        } else {
            BluetoothAdapter.getDefaultAdapter().disable();
            Toast.makeText(getApplicationContext(), "Bluetooth Disabled" ,Toast.LENGTH_LONG).show();
        }
    }

    public void list(View v){
        pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        final ArrayList list = new ArrayList();
        final ArrayList Deviceslist = new ArrayList();
        for (BluetoothDevice bt : pairedDevices) {
            list.add(bt.getName());
            Deviceslist.add(bt);

        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(), "Start Connect Thread", Toast.LENGTH_SHORT).show();
                bt = (BluetoothDevice) Deviceslist.get(position);
//                ct = new ConnectThread(bt,mHandler);
//                ct.start();
                //Toast.makeText(getApplicationContext(), "End Connect Thread", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void visible(View v){

        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }
}
