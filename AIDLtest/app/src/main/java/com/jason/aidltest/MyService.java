package com.jason.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

//This class exposes the service to client

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMyService.Stub() {

            //Implement com.jason.aidltest.IMyService.add(int, int)
            @Override
            public int add(int value1, int value2 ) throws RemoteException{
                return value1 + value2;
            }

        };
    }
}
