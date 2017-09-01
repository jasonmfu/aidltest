package com.jason.aidltest;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.Context;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AIDLtest extends AppCompatActivity {
    IMyService service;
    MyServiceConnection connection;




    //savedInstanceState from onCeate
    @Override
    protected void onCreate(Bundle savedInstanceState)//save app status
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidltest);//跳轉頁面到layout.activity_aidltest

        initService();//init service

        Button buttonCalc = (Button)findViewById(R.id.buttonCalc);//find button for id buttonCalc
        buttonCalc.setOnClickListener(new View.OnClickListener() //create button check box
        {
            EditText value1 = (EditText)findViewById(R.id.value1);//find EditText for id value1
            EditText value2 = (EditText)findViewById(R.id.value2);//find EditText for id value2
            TextView result = (TextView)findViewById(R.id.result);

            @Override
            public void onClick(View v) {
                //Toast.makeText(AIDLtest.this, "run in onClick funtion", Toast.LENGTH_LONG).show();


                int v1, v2, res = 1;
                v1 = Integer.parseInt(value1.getText().toString());
                v2 = Integer.parseInt(value2.getText().toString());

                try{

                        res = service.add(v1, v2);
                }catch (RemoteException e){
                    e.printStackTrace();
                }

                result.setText(Integer.valueOf(res).toString());
            }
        });
    }

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder boundService){
            service = IMyService.Stub.asInterface(boundService);
            Toast.makeText(AIDLtest.this, "Service Connect", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name){
            service = null;
            Toast.makeText(AIDLtest.this, "Service DisConnect", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        releaseService();
    }

    //This inner class is uesd to connect the service


    //This method connects the Activity to the service
    private void initService(){
        connection = new MyServiceConnection();
        Intent i = new Intent();
        i.setClassName("com.jason.aidltest", com.jason.aidltest.MyService.class.getName());
        bindService(i, connection,Context.BIND_AUTO_CREATE);
    }

    //This method disconnects the Activity from the service
    private void releaseService(){
        unbindService(connection);
        connection = null;
    }
}
