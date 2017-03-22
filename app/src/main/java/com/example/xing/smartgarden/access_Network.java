package com.example.xing.smartgarden;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class access_Network {

    public static boolean hasNetworkAccess(Context context) {

        ConnectivityManager Manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo Network = Manager.getActiveNetworkInfo();
            return Network != null && Network.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}