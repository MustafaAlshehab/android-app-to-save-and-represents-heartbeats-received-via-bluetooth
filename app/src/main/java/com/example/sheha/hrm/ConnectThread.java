package com.example.sheha.hrm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by sheha on 2/14/2017.
 */

public class ConnectThread extends Thread {

    ConnectThread2 mConnectThread2;
    private Handler mhandler;
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");


    public ConnectThread(BluetoothDevice device, Handler handler) {
        BluetoothSocket tmp = null;
        mmDevice = device;
        try {
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) { }
        mmSocket = tmp;
        mhandler = handler;
    }
    public void run() {
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        try {
            mmSocket.connect();
        } catch (IOException connectException) {
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }
        mConnectThread2 = new ConnectThread2(mmSocket,mhandler);
        mConnectThread2.start();
       // this.startTransmission();
    }
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
    public void startTransmission() {
        mConnectThread2.write("1".getBytes());
    }
}